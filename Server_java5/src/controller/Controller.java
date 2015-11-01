package controller;

import model.Model;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Interface Controller.
 */
public interface Controller {
	
	/**
	 * Display.
	 *
	 * @param obj the obj
	 */
	public void display(Object obj);

	/**
	 * Command.
	 *
	 * @param obj the obj
	 */
	public void command(Object obj);

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(Model model);

	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	public void setView(View view);
}
