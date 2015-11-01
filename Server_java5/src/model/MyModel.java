package model;

import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class MyModel.
 */
public class MyModel implements Model {

	/** The controller. */
	Controller controller;
	
	/** The my server. */
	MyServer myServer;

	/**
	 * Instantiates a new my model.
	 *
	 * @param port the port
	 * @param clinetHandler the clinet handler
	 * @param numOfClients the num of clients
	 * @param controller the controller
	 */
	public MyModel(int port, ClinetHandler clinetHandler, int numOfClients, Controller controller) {
		myServer = new MyServer(port, clinetHandler, numOfClients);
		this.controller = controller;
	}

	/* (non-Javadoc)
	 * @see model.Model#start()
	 */
	@Override
	public void start() {
		try {
			if (myServer.isServerRunning()==false) {
				myServer.start();
				controller.display("Server is running");
			}
			else {
				controller.display("Server already running");
			}

		} catch (Exception e) {
			controller.display("Server could not be initialized");
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see model.Model#onLineClients()
	 */
	@Override
	public void onLineClients() {
		if (myServer.isServerRunning()==true) {
			if (myServer.getClients().length==0) {
				controller.display("There are no clients connected to the srever");
			}
			controller.display(myServer.getClients());
		}
		else {
			controller.display("Server is closed");
		}
		
	}

	/* (non-Javadoc)
	 * @see model.Model#closeServer()
	 */
	@Override
	public void closeServer() {
		try {
			if (myServer.isServerRunning()==true) {
				myServer.close();
				controller.display("closing server");
			}
			else {
				controller.display("server already closed");
			}

		} catch (Exception e) {
			controller.display("The Server was closed in an unsafe mode");
			e.printStackTrace();
		}
	}

}
