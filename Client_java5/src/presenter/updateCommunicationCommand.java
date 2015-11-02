/*
 * 
 */
package presenter;

import model.Model;

// TODO: Auto-generated Javadoc
/**
 * The Class updateCommunicationCommand.
 */
public class updateCommunicationCommand implements Command {

	/** The model. */
	private Model model;

	/**
	 * Instantiates a new update communication command.
	 *
	 * @param model the model
	 */
	public updateCommunicationCommand(Model model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(presenter.DataObject)
	 */
	@Override
	public void doCommand(DataObject obj) {
		model.updateCommunication(obj);
	}

}
