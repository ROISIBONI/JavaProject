package view;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Observable;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.DataObject;

// TODO: Auto-generated Javadoc
/**
 * The Class MyViewCLI.
 */
public class MyViewCLI extends Observable implements View {

	/** The cli. */
	private CLI cli;

	/**
	 * Instantiates a new my view cli.
	 *
	 * @param in the in
	 * @param out the out
	 */
	public MyViewCLI(InputStreamReader in, OutputStreamWriter out) {
		cli = new CLI(in, out, this);
	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		cli.start();
	}

	/* (non-Javadoc)
	 * @see view.View#display(presenter.DataObject)
	 */
	@Override
	public void display(DataObject obj) {

		Object objectDisplay = obj.getData();

		if (objectDisplay instanceof String[]) {
			String[] arrString = (String[]) objectDisplay;
			for (int i = 0; i < arrString.length; i++) {
				cli.display(arrString[i]);
				cli.display();
			}
		}

		else if (objectDisplay instanceof String) {
			String string = (String) objectDisplay;
			cli.display(string);
			cli.display();
		}

		else if (objectDisplay instanceof int[][]) {
			int[][] arr = (int[][]) objectDisplay;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					cli.display(arr[i][j]);
				}
				cli.display();
			}
		}
		
		else if (objectDisplay instanceof Maze3d) {
			Maze3d maze3d = (Maze3d) objectDisplay;
			cli.display("start: "+((Maze3d) objectDisplay).getStartPosition().toString());
			cli.display();
			cli.display("goal: "+((Maze3d) objectDisplay).getGoalPosition().toString());
			cli.display();cli.display();
			obj.setData(maze3d.getMaze());
			display(obj);
		}

		else if (objectDisplay instanceof int[][][]) {
			int[][][] array = (int[][][]) objectDisplay;
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					for (int k = 0; k < array[0][0].length; k++) {
						cli.display(array[i][j][k]);
					}
					cli.display();
				}
				cli.display();
			}
		}
		
		else if (objectDisplay instanceof Solution) {
			@SuppressWarnings("unchecked")
			Solution<Position> s = (Solution<Position>) objectDisplay;
			ArrayList<Position> array =	s.getMovesSolution();
			for (int i = 0; i < array.size(); i++) {
						cli.display(array.get(i));
						cli.display();
			}

		}

		else {
			cli.display(objectDisplay);
			cli.display();
		}
	}

	/* (non-Javadoc)
	 * @see view.View#exit()
	 */
	@Override
	public void exit() {
		cli.exit();
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	@Override
	public void notifyObservers(Object obj) {
		setChanged();
		super.notifyObservers(obj);
	}

}
