package view;

import java.io.FileNotFoundException;
import java.util.Observable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.DataObject;
import presenter.ManagerProperties;
import presenter.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class CommandMazeGame.
 */
public class CommandMazeGame implements CommandGameGUI {

	/** The Constant ERROR. */
	// next commands
	public static final int ERROR 				=-1;
	
	/** The Constant GENERATE_GAME. */
	public static final int GENERATE_GAME		= 0;
	
	/** The Constant PLAY_GAME. */
	public static final int PLAY_GAME 			= 1;
	
	/** The Constant SOLVE_GAME. */
	public static final int SOLVE_GAME			= 2;
	
	/** The Constant DISPLAY_SOLUTION. */
	public static final int DISPLAY_SOLUTION 	= 3;
	
	/** The Constant EXIT. */
	public static final int EXIT 				= 4;
	
	/** The properties. */
	Properties properties;
	
	/** The maze3d. */
	Maze3d maze3d;
	
	/** The maze number. */
	int mazeNumber;
	
	/** The ob. */
	Observable ob;
	
	/** The maze game. */
	MazeGame mazeGame;
	
	/**
	 * Instantiates a new command maze game.
	 */
	public CommandMazeGame() {
		this.properties = new Properties();
		this.mazeNumber=1;
		this.maze3d = null;
		this.ob = null;
		this.mazeGame = null;
	}
	
	/* (non-Javadoc)
	 * @see view.CommandGameGUI#generateGame()
	 */
	@Override
	public void generateGame() {
		DataObject data = new DataObject(GENERATE_GAME, "generate 3d maze,maze "+mazeNumber+","+properties.getGenerateAlgorithm()+","+properties.getSizeX()+","+properties.getSizeY()+","+properties.getSizeZ());
		ob.notifyObservers(data);
	}
	
	/* (non-Javadoc)
	 * @see view.CommandGameGUI#getGameData()
	 */
	@Override
	public void getGameData() {
		DataObject data=new DataObject(PLAY_GAME,"display,maze "+mazeNumber);
		mazeNumber++;
		ob.notifyObservers(data);
	}
	
	/* (non-Javadoc)
	 * @see view.CommandGameGUI#setGameData(java.lang.Object)
	 */
	@Override
	public boolean setGameData(Object obj) {
		if (obj instanceof Maze3d) {
			maze3d = new Maze3d((Maze3d) obj);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see view.CommandGameGUI#playGame(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void playGame(Composite compositeGame) {
		if (maze3d != null && compositeGame!=null) {
			MazeDisplayer mazePaint = new Maze2D(compositeGame, SWT.DOUBLE_BUFFERED,"pic/wall.png","pic/bob sponge.jpg","pic/patrick.jpg","pic/win bob.jpg", "pic/up.png",  "pic/down.png",  "pic/up&down.png");
			//MazeDisplayer mazePaint = new Maze3D(compositeGame, SWT.DOUBLE_BUFFERED,"pic/win.jpg");
			FormData mazePaintFromData = new FormData();
			mazePaintFromData.top = new FormAttachment(0, 0);
			mazePaintFromData.bottom = new FormAttachment(100, 0);
			mazePaintFromData.left = new FormAttachment(0, 0);
			mazePaintFromData.right = new FormAttachment(100, 0);
			mazePaint.setLayoutData(mazePaintFromData);
			mazeGame = new MazeGame(mazePaint,maze3d);
			mazeGame.play();
		}
	}
	
	/* (non-Javadoc)
	 * @see view.CommandGameGUI#openProperties(java.lang.String)
	 */
	@Override
	public void openProperties(String filePath) {
		if (filePath!=null) {
			ManagerProperties manager = new ManagerProperties();
			try {
				properties = manager.loadFile(filePath);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}	
		}
	}
	
	/* (non-Javadoc)
	 * @see view.CommandGameGUI#setProperties(presenter.Properties)
	 */
	@Override
	public void setProperties(Properties properties) {
		this.properties=properties;	
	}

	/* (non-Javadoc)
	 * @see view.CommandGameGUI#getProperties()
	 */
	@Override
	public Properties getProperties() {
		return new Properties(properties);
	}
	
	/* (non-Javadoc)
	 * @see view.CommandGameGUI#getGameName()
	 */
	@Override
	public String getGameName() {
		return "Maze Game";
	}

	/* (non-Javadoc)
	 * @see view.CommandGameGUI#sloveGame()
	 */
	@Override
	public void sloveGame() {
		if (mazeGame!=null) {
			DataObject data=new DataObject(SOLVE_GAME,"solve,maze "+(mazeNumber-1)+","+properties.getSolveAlgorithm()+","+mazeGame.getCurrentPosition());
			ob.notifyObservers(data);
		}
	}
	
	/* (non-Javadoc)
	 * @see view.CommandGameGUI#getSolutionData()
	 */
	@Override
	public void getSolutionData() {
		if (mazeGame!=null) {
			DataObject data=new DataObject(DISPLAY_SOLUTION,"display solution,maze "+(mazeNumber-1)+"*");
			ob.notifyObservers(data);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see view.CommandGameGUI#displaySloveGame(java.lang.Object)
	 */
	@Override
	public void displaySloveGame(Object obj) {
		if (mazeGame!=null) {
			if (obj instanceof Solution) {
				@SuppressWarnings("unchecked")
				Solution<Position> s = (Solution<Position>) obj;
				mazeGame.displaySolution(s);
			}
		}
	}

	/* (non-Javadoc)
	 * @see view.CommandGameGUI#setObservable(java.util.Observable)
	 */
	@Override
	public void setObservable(Observable ob) {
		this.ob=ob;	
	}
	
	/* (non-Javadoc)
	 * @see view.CommandGameGUI#pause()
	 */
	@Override
	public void pause() {
		if (mazeGame!=null) {
			mazeGame.pause();
		}
	}
	
	/* (non-Javadoc)
	 * @see view.CommandGameGUI#closeGame()
	 */
	@Override
	public void closeGame() {
		if (mazeGame!=null) {
			mazeGame.close();
		}
		
	}

	
	/* (non-Javadoc)
	 * @see view.CommandGameGUI#exit()
	 */
	@Override
	public void exit() {
		DataObject data=new DataObject(EXIT,"exit");
		ob.notifyObservers(data);	
	}

}
