package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

// TODO: Auto-generated Javadoc
/**
 * The Class Maze2D.
 */
public class Maze2D extends MazeDisplayer{

	/** The character x. */
	public int characterX;
	
	/** The character y. */
	public int characterY;
	
	/** The exit x. */
	public int exitX;
	
	/** The exit y. */
	public int exitY;
	
	/** The imgae. */
	public Image imgae;

	 /**
 	 * Instantiates a new maze2 d.
 	 *
 	 * @param parent the parent
 	 * @param style the style
 	 * @param wallPathImage the wall path image
 	 * @param characterPathImage the character path image
 	 * @param goalPathImage the goal path image
 	 * @param winPathImage the win path image
 	 * @param upPathImage the up path image
 	 * @param downPathImage the down path image
 	 * @param upDownPathImage the up down path image
 	 */
 	public Maze2D(Composite parent,int style, String wallPathImage, String characterPathImage, String goalPathImage, String winPathImage, String upPathImage, String downPathImage, String upDownPathImage){
	        super(parent, style);
	    	// set a white background   (red, green, blue)
	    	setBackground(new Color(null, 255, 255, 255));
	    	
	    	Image imageWall = new Image(getDisplay(), wallPathImage);
	    	Image imageCharacter = new Image(getDisplay(), characterPathImage);
	    	Image imageGoal = new Image(getDisplay(), goalPathImage);
	    	Image imageWin = new Image(getDisplay(), winPathImage);	
	    	Image imageUp = new Image(getDisplay(), upPathImage);
	    	Image imageDown = new Image(getDisplay(), downPathImage);
	    	Image imageUpDown = new Image(getDisplay(), upDownPathImage);
	    	
			addPaintListener(new PaintListener() {
	
				@Override
				public void paintControl(PaintEvent e) {
					// e.gc.setForeground(new Color(null,0,0,0));
					e.gc.setBackground(new Color(null, 0, 0, 0));
	
					int width = getSize().x;
					int height = getSize().y;
	
					int w = width / mazeData[0].length;
					int h = height / mazeData.length;
	
					for (int i = 0; i < mazeData.length; i++)
						for (int j = 0; j < mazeData[i].length; j++) {
							int x = j * w;
							int y = i * h;
							if (characterX == exitX && characterY == exitY) {
					        	  e.gc.drawImage(imageWin, 0, 0, imageWin.getBounds().width, imageWin.getBounds().height,0, 0, (int)width,(int) height);
					        	  return;
							} else {
								if (mazeData[i][j] == 1){
									// e.gc.fillRectangle(x,y,w,h);
									e.gc.drawImage(imageWall, 0, 0, imageWall.getBounds().width,
											imageWall.getBounds().height, x, y, w, h);
								}
								else if (mazeData[i][j] == 2) {
									e.gc.drawImage(imageUp, 0, 0, imageUp.getBounds().width,
											imageUp.getBounds().height, x, y, w, h);
								}
								else if (mazeData[i][j] == 3) {
									e.gc.drawImage(imageDown, 0, 0, imageDown.getBounds().width,
											imageDown.getBounds().height, x, y, w, h);
								}
								else if (mazeData[i][j] == 4) {
									e.gc.drawImage(imageUpDown, 0, 0, imageUpDown.getBounds().width,
											imageUpDown.getBounds().height, x, y, w, h);
								}
								if (j == characterX && i == characterY) {
									// e.gc.setBackground(new Color(null,255,0,0));
									// e.gc.fillRectangle(x, y, w, h);
									// e.gc.setBackground(new Color(null,0,0,0));
									e.gc.drawImage(imageCharacter, 0, 0, imageCharacter.getBounds().width,
											imageCharacter.getBounds().height, x, y, w, h);
								}
								if (j == exitX && i == exitY) {
									// e.gc.setBackground(new Color(null,0,255,0));
									// e.gc.fillRectangle(x, y, w, h);
									// e.gc.setBackground(new Color(null,0,0,0));
									e.gc.drawImage(imageGoal, 0, 0, imageGoal.getBounds().width,
											imageGoal.getBounds().height, x, y, w, h);
								}
							}
						}
				}
			});
		}

		/**
		 * Move character.
		 *
		 * @param x the x
		 * @param y the y
		 * @return true, if successful
		 */
		private boolean moveCharacter(int x,int y){
			if(x>=0 && x<mazeData[0].length && y>=0 && y<mazeData.length && mazeData[y][x]!=1){
				characterX=x;
				characterY=y;
				getDisplay().syncExec(new Runnable() {
					
					@Override
					public void run() {
						redraw();
					}
				});
				return true;
			}
			return false;
		}
		
		/* (non-Javadoc)
		 * @see view.MazeDisplayer#moveUp()
		 */
		@Override
		public boolean moveUp() {
			int x=characterX;
			int y=characterY;
			y=y-1;
			return moveCharacter(x, y);

		}
		/* (non-Javadoc)
		 * @see view.MazeDisplayer#moveDown()
		 */
		@Override
		public boolean moveDown() {
			int x=characterX;
			int y=characterY;
			y=y+1;
			return moveCharacter(x, y);
		}
		
		/* (non-Javadoc)
		 * @see view.MazeDisplayer#moveLeft()
		 */
		@Override
		public boolean moveLeft() {
			int x=characterX;
			int y=characterY;
			x=x-1;
			return moveCharacter(x, y);
		}
		
		/* (non-Javadoc)
		 * @see view.MazeDisplayer#moveRight()
		 */
		@Override
		public boolean moveRight() {
			int x=characterX;
			int y=characterY;
			x=x+1;
			return moveCharacter(x, y);
		}
		
		/* (non-Javadoc)
		 * @see view.MazeDisplayer#setCharacterPosition(int, int)
		 */
		@Override
		public boolean setCharacterPosition(int row, int col) {
			characterX=col;
			characterY=row;
			return moveCharacter(col,row);
		}

		/* (non-Javadoc)
		 * @see view.MazeDisplayer#setGoaldPosition(int, int)
		 */
		@Override
		public void setGoaldPosition(int row, int col) {
			exitX = col;
			exitY = row;
			getDisplay().syncExec(new Runnable() {
				
				@Override
				public void run() {
					redraw();
				}
			});
		}

}
