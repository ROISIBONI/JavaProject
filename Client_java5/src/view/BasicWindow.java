/*
 * 
 */
package view;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

// TODO: Auto-generated Javadoc
/**
 * The Class BasicWindow.
 */
public abstract class BasicWindow extends Observable implements Runnable {

	/** The display. */
	Display display;
	
	/** The shell. */
	Shell shell;

	/**
	 * Instantiates a new basic window.
	 *
	 * @param title the title
	 * @param width the width
	 * @param height the height
	 */
	public BasicWindow(String title, int width, int height) {
		display = new Display();
		shell = new Shell(display);
		shell.setSize(width, height);
		shell.setText(title);
	}

	/**
	 * Instantiates a new basic window.
	 *
	 * @param title the title
	 * @param pathImage the path image
	 */
	public BasicWindow(String title, String pathImage) {
		display = new Display();
		shell = new Shell(display);
		shell.setText(title);
		loadBackgroundImage(pathImage, shell);
	}

	/**
	 * Inits the widgets.
	 */
	abstract void initWidgets();

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		initWidgets();

		shell.open();

		// main event loop
		while (!shell.isDisposed()) { // while window isn't closed

			// 1. read events, put then in a queue.
			// 2. dispatch the assigned listener
			if (!display.readAndDispatch()) { // if the queue is empty
				display.sleep(); // sleep until an event occurs
			}

		} // shell is disposed

		display.dispose(); // dispose OS components
	}

	/**
	 * Load background image.
	 *
	 * @param path the path
	 * @param shell the shell
	 */
	public void loadBackgroundImage(String path, Shell shell) {

		try {
			Image image = new Image(shell.getDisplay(), path);
			Rectangle rect = image.getBounds();
			shell.setSize(rect.width, rect.height);
			shell.setMinimumSize(rect.width, rect.height);
			shell.setBackgroundImage(image);
			
			shell.addListener(SWT.Resize, new Listener() {
			
				@Override
				public void handleEvent(Event arg0) {
					ImageData data = image.getImageData();
					Image m = new Image(shell.getDisplay(),
							data.scaledTo(shell.getClientArea().width, shell.getClientArea().height));
					shell.setBackgroundImage(m);
				}
			});
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot load image - " + path);
			System.exit(1);
		}
	}
	
}
