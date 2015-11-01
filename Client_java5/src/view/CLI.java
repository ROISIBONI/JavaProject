package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Observable;
import presenter.DataObject;

// TODO: Auto-generated Javadoc
/**
 * The Class CLI.
 */
public class CLI {

	/** The thread. */
	Thread thread;
	
	/** The br. */
	BufferedReader br;
	
	/** The pw. */
	PrintWriter pw;
	
	/** The ob. */
	Observable ob;
	
	/** The run thread. */
	boolean runThread;


	/**
	 * Instantiates a new cli.
	 *
	 * @param in the in
	 * @param out the out
	 * @param ob the ob
	 */
	public CLI(InputStreamReader in, OutputStreamWriter out, Observable ob) {
		this.br = new BufferedReader(in);
		this.pw = new PrintWriter(out);
		this.runThread = true;
		this.ob=ob;
	}

	/**
	 * Start.
	 */
	public void start() {
		
		ob.notifyObservers(new DataObject(0, "display commands"));

		new Thread(new Runnable() {

			@Override
			public void run() {
				do {
					try {
						String line = br.readLine();
						if (!(line.equals(""))) {
							ob.notifyObservers(new DataObject(0, line));
						} else {
							pw.println("empty Command");
							pw.flush();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} while (runThread);
			}
		}).start();
	}

	/**
	 * Exit.
	 */
	public void exit() {
		runThread = false;
		pw.println("The program closes - Thank you and goodbye");
		pw.flush();
	}

	/**
	 * Display.
	 *
	 * @param o the o
	 */
	public void display(Object o) {
		pw.print(o);
		pw.flush();
	}

	/**
	 * Display.
	 */
	public void display() {
		pw.println();
		pw.flush();
	}

}
