/*
 * 
 */
package model;

import presenter.DataObject;

// TODO: Auto-generated Javadoc
/**
 * The Interface Model.
 */
public interface Model {


	/**
	 * Generate3d maze.
	 *
	 * @param obj the obj
	 */
	public void generate3dMaze (DataObject obj);

	/**
	 * Display maze.
	 *
	 * @param obj the obj
	 */
	public void displayMaze (DataObject obj);

	/**
	 * Display cross section.
	 *
	 * @param obj the obj
	 */
	public void displayCrossSection(DataObject obj);

	/**
	 * Save maze.
	 *
	 * @param obj the obj
	 */
	public void saveMaze(DataObject obj);

	/**
	 * Load maze.
	 *
	 * @param obj the obj
	 */
	public void loadMaze(DataObject obj);

	/**
	 * Maze size.
	 *
	 * @param obj the obj
	 */
	public void mazeSize(DataObject obj);

	/**
	 * Solve maze.
	 *
	 * @param obj the obj
	 */
	public void solveMaze(DataObject obj);

	/**
	 * Display solution.
	 *
	 * @param obj the obj
	 */
	public void displaySolution(DataObject obj);
	
	public void updateCommunication(DataObject obj);

	/**
	 * Exit.
	 */
	public void exit();
}
