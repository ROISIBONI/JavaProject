package presenter;

import model.Model;

// TODO: Auto-generated Javadoc
/**
 * The Class loadMazeCommand.
 */
public class loadMazeCommand implements Command {

	/** The model. */
	private Model model;

	/**
	 * Instantiates a new load maze command.
	 *
	 * @param model the model
	 */
	public loadMazeCommand(Model model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(presenter.DataObject)
	 */
	@Override
	public void doCommand(DataObject obj) {
		model.loadMaze(obj);
	}

}
