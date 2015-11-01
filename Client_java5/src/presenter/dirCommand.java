package presenter;

import java.io.File;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class dirCommand.
 */
public class dirCommand implements Command {

	/** The view. */
	private View view;

	/**
	 * Instantiates a new dir command.
	 *
	 * @param view the view
	 */
	public dirCommand(View view) {
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

			if (file.length() != 0) {

				String[] list = file.list();

				if (list == null) {
					obj.setData("empty dir or not exist dir");
					obj.setSerialNumber(-1);
					view.display(obj);
				}

				else {
					obj.setData(list);
					view.display(obj);
				}
			}

			else {
				obj.setData("empty dir or not exist dir");
				obj.setSerialNumber(-1);
				view.display(obj);
			}
		}

		else {
			obj.setData("no writer path");
			obj.setSerialNumber(-1);
			view.display(obj);
		}
	}

}
