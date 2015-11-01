package presenter;

import model.Model;

// TODO: Auto-generated Javadoc
/**
 * The Class displayCommand.
 */
public class displayCommand implements Command {

	/** The model. */
	private Model model;

	/**
	 * Instantiates a new display command.
	 *
	 * @param model the model
	 */
	public displayCommand(Model model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(presenter.DataObject)
	 */
	@Override
	public void doCommand(DataObject obj) {
		model.displayMaze(obj);
	}

}
