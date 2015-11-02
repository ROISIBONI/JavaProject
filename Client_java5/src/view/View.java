/*
 * 
 */
package view;

import presenter.DataObject;

// TODO: Auto-generated Javadoc
/**
 * The Interface View.
 */
public interface View {

	/**
	 * Start.
	 */
	public void start();

	/**
	 * Display.
	 *
	 * @param obj the obj
	 */
	public void display (DataObject obj);

	/**
	 * Exit.
	 */
	public void exit();
	
}
