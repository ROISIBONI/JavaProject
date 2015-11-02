/*
 * 
 */
package view;

import java.util.Observable;
import org.eclipse.swt.widgets.Composite;
import presenter.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Interface CommandGameGUI.
 */
public interface CommandGameGUI {
	
	/**
	 * Gets the game name.
	 *
	 * @return the game name
	 */
	public String getGameName();

	/**
	 * Generate game.
	 */
	public void generateGame ();
	
	/**
	 * Gets the game data.
	 *
	 * @return the game data
	 */
	public void getGameData();
	
	/**
	 * Sets the game data.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	public boolean setGameData(Object obj);
	
	/**
	 * Play game.
	 *
	 * @param compositeGame the composite game
	 */
	public void playGame(Composite compositeGame);
	
	/**
	 * Slove game.
	 */
	public void sloveGame();
	
	/**
	 * Gets the solution data.
	 *
	 * @return the solution data
	 */
	public void getSolutionData();
	
	/**
	 * Display slove game.
	 *
	 * @param obj the obj
	 */
	public void displaySloveGame(Object obj);
	
	/**
	 * Open properties.
	 *
	 * @param filePath the file path
	 */
	public void openProperties (String filePath);
	
	/**
	 * Sets the properties.
	 *
	 * @param Properties the new properties
	 */
	public void setProperties (Properties Properties);
	
	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public Properties getProperties ();
	
	/**
	 * Update communication data.
	 */
	public void updateCommunicationData ();
	
	/**
	 * Sets the observable.
	 *
	 * @param ob the new observable
	 */
	public void setObservable (Observable ob);
	
	/**
	 * Pause.
	 */
	public void pause();
	
	/**
	 * Close game.
	 */
	public void closeGame();
	
	/**
	 * Exit.
	 */
	public void exit();
		
}
