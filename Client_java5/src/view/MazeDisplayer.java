package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

// TODO: Auto-generated Javadoc
/**
 * The Class MazeDisplayer.
 */
public abstract class MazeDisplayer extends Canvas{
	
	/** The maze data. */
	int[][] mazeData;/*={
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
			{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
			{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
			{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
			{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
			{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
			{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
			{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1},
	};*/
	
	/**
 * Instantiates a new maze displayer.
 *
 * @param parent the parent
 * @param style the style
 */
public MazeDisplayer(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * Sets the maze data.
	 *
	 * @param mazeData the new maze data
	 */
	public void setMazeData(int[][] mazeData){
		this.mazeData = mazeData ;
	}
	
	/**
	 * Sets the character position.
	 *
	 * @param row the row
	 * @param col the col
	 * @return true, if successful
	 */
	public abstract boolean setCharacterPosition(int row,int col);
	
	/**
	 * Sets the goald position.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public abstract void setGoaldPosition(int row,int col);

	/**
	 * Move up.
	 *
	 * @return true, if successful
	 */
	public abstract boolean moveUp();

	/**
	 * Move down.
	 *
	 * @return true, if successful
	 */
	public abstract boolean moveDown();

	/**
	 * Move left.
	 *
	 * @return true, if successful
	 */
	public abstract boolean moveLeft();

	/**
	 * Move right.
	 *
	 * @return true, if successful
	 */
	public abstract boolean moveRight();

}