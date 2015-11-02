/*
 * 
 */
package presenter;

import model.Model;

// TODO: Auto-generated Javadoc
/**
 * The Class generate3dMazeCommand.
 */
public class generate3dMazeCommand implements Command {

	/** The model. */
	private Model model;

	/**
	 * Instantiates a new generate3d maze command.
	 *
	 * @param model the model
	 */
	public generate3dMazeCommand(Model model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(presenter.DataObject)
	 */
	@Override
	public void doCommand(DataObject obj) {
		model.generate3dMaze(obj);
	}

}
