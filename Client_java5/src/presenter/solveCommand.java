/*
 * 
 */
package presenter;

import model.Model;

// TODO: Auto-generated Javadoc
/**
 * The Class solveCommand.
 */
public class solveCommand implements Command {

	/** The model. */
	private Model model;

	/**
	 * Instantiates a new solve command.
	 *
	 * @param model the model
	 */
	public solveCommand(Model model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(presenter.DataObject)
	 */
	@Override
	public void doCommand(DataObject obj) {
		model.solveMaze(obj);
	}

}
