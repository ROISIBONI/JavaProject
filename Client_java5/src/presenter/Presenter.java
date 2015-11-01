package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import model.Model;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class Presenter.
 */
public class Presenter implements Observer {

	/** The view. */
	private View view;
	
	/** The model. */
	@SuppressWarnings("unused")
	private Model model;
	
	/** The command. */
	private HashMap<String, Command> command;

	/**
	 * Instantiates a new presenter.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public Presenter(View view, Model model) {

		this.view = view;
		this.model = model;

		this.command = new HashMap<String, Command>();
		command.put("dir", new dirCommand(view));
		command.put("generate 3d maze", new generate3dMazeCommand(model));
		command.put("display", new displayCommand(model));
		command.put("display cross section", new displayCrossSectionCommand(model));
		command.put("save maze", new saveMazeCommand(model));
		command.put("load maze", new loadMazeCommand(model));
		command.put("maze size", new mazeSizeCommand(model));
		command.put("file size", new fileSizeCommand(view));
		command.put("solve", new solveCommand(model));
		command.put("display solution", new displaySolutionCommand(model));
		command.put("exit", new exitCommand(model, view));
		command.put("display commands", new displayAllCommand(view));
	}

	/**
	 * Display.
	 *
	 * @param o the o
	 */
	public void display(DataObject o) {
		view.display(o);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable observable, Object obj) {
		try {
			if (obj instanceof DataObject) {

				DataObject o = (DataObject) obj;

				if (observable instanceof Model) {
					display(o);
				}
				
				else if (observable instanceof View) {

					if (o.getData() instanceof String) {

						String string = (String) o.getData();
						
						String[] arr = string.split(",", 2);
						
						Command c = command.get(arr[0]);

						if (c != null) {
							if (arr.length > 1) {
								o.setData(arr[1]);
							}
							else {
								o.setData(null);
							}
							c.doCommand(o);
						}

						else {
							o.setData("command not exist");
							o.setSerialNumber(-1);
							display(o);
						}
					}

					else {
						throw new Exception("o.getData() - need be string");
					}
				}
				else {
					throw new Exception("the received message is not from Model or View");
				}
			}

			else {
				throw new Exception("Object not instanceof DataObject");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
