/*
 * 
 */
package presenter;

import model.Model;

// TODO: Auto-generated Javadoc
/**
 * The Class mazeSizeCommand.
 */
public class mazeSizeCommand implements Command {

	/** The model. */
	private Model model;

	/**
	 * Instantiates a new maze size command.
	 *
	 * @param model the model
	 */
	public mazeSizeCommand(Model model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(presenter.DataObject)
	 */
	@Override
	public void doCommand(DataObject obj) {
		model.mazeSize(obj);
	}

}
