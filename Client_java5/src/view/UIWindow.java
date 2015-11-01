package view;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import model.MyModel;
import presenter.Presenter;

// TODO: Auto-generated Javadoc
/**
 * The Class UIWindow.
 */
public class UIWindow extends BasicWindow {

	/**
	 * Instantiates a new UI window.
	 *
	 * @param title the title
	 * @param width the width
	 * @param height the height
	 */
	public UIWindow(String title, int width, int height) {
		super(title, width, height);
	}

	/**
	 * Instantiates a new UI window.
	 *
	 * @param title the title
	 * @param path the path
	 */
	public UIWindow(String title, String path) {
		super(title, path);
	}

	/* (non-Javadoc)
	 * @see view.BasicWindow#initWidgets()
	 */
	@Override
	void initWidgets() {

		shell.setLayout(new FormLayout());

		Cursor handCursor = new Cursor(display, SWT.CURSOR_HAND);
		shell.setCursor(handCursor);

		Text text = new Text(shell, SWT.CENTER | SWT.READ_ONLY);
		text.setText("Please Select Interface");

		FormData textFromData = new FormData();
		textFromData.top = new FormAttachment(0, 5);
		textFromData.bottom = new FormAttachment(20, -5);
		textFromData.left = new FormAttachment(0, 5);
		textFromData.right = new FormAttachment(100, -5);
		text.setLayoutData(textFromData);

		Font font = new Font(text.getDisplay(), new FontData("Cooper Black", 25, SWT.BOLD));
		text.setFont(font);
		// text.setForeground(new Color(display, 5, 50, 70, 100));
		text.setBackground(new Color(display, 0, 0, 0, 0));

		Button gui = new Button(shell, SWT.PUSH | SWT.TOGGLE);
		gui.setText("GUI");

		FormData guiFormData = new FormData();
		guiFormData.top = new FormAttachment(30, 10);
		guiFormData.bottom = new FormAttachment(50, -10);
		guiFormData.left = new FormAttachment(40, 10);
		guiFormData.right = new FormAttachment(60, -10);
		gui.setLayoutData(guiFormData);

		gui.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						MyModel model = new MyModel(3);
						MyViewGUI gameGUI = new MyViewGUI("Menu Game","pic/GUIWindow.jpg", new CommandMazeGame());
						Presenter presenter = new Presenter(gameGUI, model);
						gameGUI.addObserver(presenter);
						model.addObserver(presenter);
						gameGUI.start();
					}
				}).start();
				
				shell.dispose();
			}
		});

		Button cli = new Button(shell, SWT.PUSH);
		cli.setText("CLI");

		FormData cliFormData = new FormData();
		cliFormData.top = new FormAttachment(60, 10);
		cliFormData.bottom = new FormAttachment(80, -10);
		cliFormData.left = new FormAttachment(40, 10);
		cliFormData.right = new FormAttachment(60, -10);
		cli.setLayoutData(cliFormData);

		cli.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				MyModel model = new MyModel(3);
				MyViewCLI view= new MyViewCLI(new InputStreamReader(System.in), new OutputStreamWriter(System.out));
				Presenter presenter = new Presenter(view, model);
				view.addObserver(presenter);
				model.addObserver(presenter);
				view.start();
				shell.dispose();
			}
		});
		
		Text copyright = new Text(shell, SWT.CENTER | SWT.READ_ONLY);
		copyright.setText("Copyright @ ROI SIBONI and OR TAL");

		FormData copyrightFromData = new FormData();
		//copyrightFromData.top = new FormAttachment(0, 5);
		copyrightFromData.bottom = new FormAttachment(95, -5);
		copyrightFromData.left = new FormAttachment(20, 0);
		copyrightFromData.right = new FormAttachment(80, 0);
		copyright.setLayoutData(copyrightFromData);

		Font copyrightFont = new Font(text.getDisplay(), new FontData("Jokerman", 11,SWT.BOLD));
		copyright.setFont(copyrightFont);
		copyright.setForeground(new Color(display, 255, 255, 255, 0));
		copyright.setBackground(new Color(display, 0, 0, 0, 0));
	}

}
