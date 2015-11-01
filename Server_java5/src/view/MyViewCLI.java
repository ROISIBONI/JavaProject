package view;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class MyViewCLI.
 */
public class MyViewCLI implements View {

	/** The cli. */
	CLI cli;
	
	/** The controller. */
	Controller controller;

	/**
	 * Instantiates a new my view cli.
	 *
	 * @param in the in
	 * @param out the out
	 * @param controller the controller
	 */
	public MyViewCLI(InputStreamReader in, OutputStreamWriter out, Controller controller) {
		cli = new CLI(in, out, controller);
		this.controller = controller;
	}

	/* (non-Javadoc)
	 * @see view.View#display(java.lang.Object)
	 */
	@Override
	public void display(Object obj) {
		cli.display(obj);
	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		cli.start();
	}

	/* (non-Javadoc)
	 * @see view.View#exit()
	 */
	@Override
	public void exit() {
		cli.exit();
	}

}
