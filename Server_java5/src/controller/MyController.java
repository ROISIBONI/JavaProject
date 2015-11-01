package controller;

import java.util.HashMap;
import model.Model;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class MyController.
 */
public class MyController implements Controller {

	/** The model. */
	Model model;
	
	/** The view. */
	View view;
	
	/** The command. */
	HashMap<String, Command> command;

	/**
	 * Instantiates a new my controller.
	 */
	public MyController() {
		command = new HashMap<String, Command>();
		command.put("display command", new displayCommand());
		command.put("open server", new openServer());
		command.put("online clients", new OnlineClients());
		command.put("close server", new CloseServer());
		command.put("exit", new Exit());
	}

	/* (non-Javadoc)
	 * @see controller.Controller#setModel(model.Model)
	 */
	@Override
	public void setModel(Model model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see controller.Controller#setView(view.View)
	 */
	@Override
	public void setView(View view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see controller.Controller#display(java.lang.Object)
	 */
	@Override
	public void display(Object obj) {
		view.display(obj);
	}

	/* (non-Javadoc)
	 * @see controller.Controller#command(java.lang.Object)
	 */
	@Override
	public void command(Object obj) {
		if (obj instanceof String) {
			String string = (String) obj;
			Command c = command.get(string.toLowerCase());
			if (c != null) {
				c.doCommand();
			} else {
				view.display("wrong Command");
			}
		} else {
			try {
				throw new Exception("Wrong input : incorrect object");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * The Class displayCommand.
	 */
	public class displayCommand implements Command {
		
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand() {
			String[] allCommand = new String[command.size()+2];
			
			allCommand[0] = "------Commands------";
			allCommand[1] = "Display command";
			allCommand[2] = "Open server";
			allCommand[3] = "Online clients";
			allCommand[4] = "Close server";
			allCommand[5] = "Exit";
			allCommand[6] = "--------------------";
			
			view.display(allCommand);
		}
	}

	/**
	 * The Class openServer.
	 */
	public class openServer implements Command {
		
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand() {
			model.start();
		}
	}

	/**
	 * The Class OnlineClients.
	 */
	public class OnlineClients implements Command {
		
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand() {
			model.onLineClients();
		}
	}

	/**
	 * The Class CloseServer.
	 */
	public class CloseServer implements Command {
		
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand() {
			model.closeServer();
		}
	}

	/**
	 * The Class Exit.
	 */
	public class Exit implements Command {
		
		/* (non-Javadoc)
		 * @see controller.Command#doCommand()
		 */
		@Override
		public void doCommand() {
			model.closeServer();
			view.exit();
		}
	}

}
