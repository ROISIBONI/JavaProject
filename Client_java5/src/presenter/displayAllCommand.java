package presenter;

import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class displayAllCommand.
 */
public class displayAllCommand implements Command {

	/** The view. */
	View view;

	/**
	 * Instantiates a new display all command.
	 *
	 * @param view the view
	 */
	public displayAllCommand(View view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(presenter.DataObject)
	 */
	@Override
	public void doCommand(DataObject obj) {

		if (obj.getData()!=null) {
			obj.setData("Invalid number of parameters");
			obj.setSerialNumber(-1);
			view.display(obj);
		}
		String[] arr = new String[12+2];
		
		arr[0] = "------------------------Commands------------------------";
		arr[1] = "dir,path";
		arr[2] = "generate 3d maze,maze name,algorithm builder,floors,rows,columns";
		arr[3] = "display,maze name";
		arr[4] = "display cross section,maze name,x-floor/y-rows/z-column,number";
		arr[5] = "save maze,maze name,file name";
		arr[6] = "load maze,file name,maze name";
		arr[7] = "maze size,maze name";
		arr[8] = "file size,file name";
		arr[9] = "solve,maze name,algorithm solution heuristic*,current position*";
		arr[10] = "display solution,maze name";
		arr[11] = "display Commands";
		arr[12] = "exit";
		arr[13] = "------------------------------------------------------";

		obj.setData(arr);
		view.display(obj);

	}

}
