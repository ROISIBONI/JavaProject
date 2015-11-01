package presenter;

import model.Model;

// TODO: Auto-generated Javadoc
/**
 * The Class saveMazeCommand.
 */
public class saveMazeCommand implements Command {

	/** The model. */
	private Model model;

	/**
	 * Instantiates a new save maze command.
	 *
	 * @param model the model
	 */
	public saveMazeCommand(Model model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(presenter.DataObject)
	 */
	@Override
	public void doCommand(DataObject obj) {
		model.saveMaze(obj);
	}

}
