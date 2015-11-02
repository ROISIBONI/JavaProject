/*
 * 
 */
package presenter;

import model.Model;

// TODO: Auto-generated Javadoc
/**
 * The Class displaySolutionCommand.
 */
public class displaySolutionCommand implements Command {

	/** The model. */
	private Model model;

	/**
	 * Instantiates a new display solution command.
	 *
	 * @param model the model
	 */
	public displaySolutionCommand(Model model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(presenter.DataObject)
	 */
	@Override
	public void doCommand(DataObject obj) {
		model.displaySolution(obj);
	}

}
