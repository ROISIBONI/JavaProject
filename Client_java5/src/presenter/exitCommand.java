package presenter;

import model.Model;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class exitCommand.
 */
public class exitCommand implements Command {

	/** The model. */
	private Model model;
	
	/** The view. */
	private View view;

	/**
	 * Instantiates a new exit command.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public exitCommand(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(presenter.DataObject)
	 */
	@Override
	public void doCommand(DataObject obj) {
		if (obj.getData()!=null) {
			obj.setData("Invalid number of parameters");
			obj.setSerialNumber(-1);
			view.display(obj);
		}
		model.exit();
		view.exit();
	}

}
