/*
 * 
 */
package presenter;

import java.io.File;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class fileSizeCommand.
 */
public class fileSizeCommand implements Command {

	/** The view. */
	private View view;

	/**
	 * Instantiates a new file size command.
	 *
	 * @param view the view
	 */
	public fileSizeCommand(View view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(presenter.DataObject)
	 */
	@Override
	public void doCommand(DataObject obj) {

		String param = (String) obj.getData();

		if (param != null) {

			File file = new File(param);

			if (file == null || file.length() == 0) {
				obj.setData("Does not exist such file");
				obj.setSerialNumber(-1);
				view.display(obj);
			}

			else {
				obj.setData(file.length() + " byte");
				view.display(obj);
			}
		}

		else {
			obj.setData("wrong parameters");
			obj.setSerialNumber(-1);
			view.display(obj);
		}
	}

}
