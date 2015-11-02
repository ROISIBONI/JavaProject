/*
 * 
 */
package presenter;

import model.Model;

// TODO: Auto-generated Javadoc
/**
 * The Class displayCrossSectionCommand.
 */
public class displayCrossSectionCommand implements Command {

	/** The model. */
	private Model model;

	/**
	 * Instantiates a new display cross section command.
	 *
	 * @param model the model
	 */
	public displayCrossSectionCommand(Model model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(presenter.DataObject)
	 */
	@Override
	public void doCommand(DataObject obj) {
		model.displayCrossSection(obj);
	}

}
