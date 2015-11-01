package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class CLI.
 */
public class CLI {

	/** The controller. */
	Controller controller;
	
	/** The thread. */
	Thread thread;
	
	/** The br. */
	BufferedReader br;
	
	/** The pw. */
	PrintWriter pw;
	
	/** The run thread. */
	boolean runThread;

	/**
	 * Instantiates a new cli.
	 *
	 * @param in the in
	 * @param out the out
	 * @param controller the controller
	 */
	public CLI(InputStreamReader in, OutputStreamWriter out, Controller controller) {
		this.br = new BufferedReader(in);
		this.pw = new PrintWriter(out);
		this.controller = controller;
		this.thread = null;
		this.runThread = true;
	}

	/**
	 * Start.
	 */
	public void start() {
		controller.command("display command");
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				do {
					try {
						String line = br.readLine();
						if (line != "") {
							controller.command(line);
						} else {
							pw.println("wrong Command");
							pw.flush();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} while (runThread);
			}
		});
		thread.start();
	}

	/**
	 * Exit.
	 */
	public void exit() {
		runThread = false;
		pw.println("Closes the program - Thank you and goodbye");
		pw.flush();
	}

	/**
	 * Display.
	 *
	 * @param o the o
	 */
	public void display(Object o) {
		if (o instanceof String[]) {
			String[] arrString = (String[]) o;
			for (int i = 0; i < arrString.length; i++) {
				pw.println(arrString[i]);
				pw.flush();
			}
		} else if (o instanceof String) {
			String string = (String) o;
			pw.println(string);
			pw.flush();
		} else {
			pw.println(o);
			pw.flush();
		}
	}

}