package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

// TODO: Auto-generated Javadoc
/**
 * The Class GameDialog.
 */
public class GameDialog extends BasicDialog {

	/** The game command. */
	CommandGameGUI gameCommand;
	
	/**
	 * Instantiates a new game dialog.
	 *
	 * @param shell the shell
	 * @param text the text
	 * @param gameCommand the game command
	 */
	public GameDialog(Shell shell, String text,CommandGameGUI gameCommand) {
		super(shell, text);
		this.gameCommand=gameCommand;
	}

	/* (non-Javadoc)
	 * @see view.BasicDialog#initWidgets(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	void initWidgets(Shell dialog) {
		
		dialog.setLayout(new FormLayout());
		
		RowLayout vertical = new RowLayout();
		vertical.type = SWT.VERTICAL;
		
		Cursor handCursor = new Cursor(dialog.getDisplay(), SWT.CURSOR_HAND);
		dialog.setCursor(handCursor);
		
		dialog.setMinimumSize(1000, 640);
		
		Composite leftPart = new Composite(dialog, 0);

		FormData leftPartFormData = new FormData();
		leftPartFormData.top = new FormAttachment(0, 7);
		leftPartFormData.bottom = new FormAttachment(100, -7);
		leftPartFormData.left = new FormAttachment(0, 7);
		leftPart.setLayoutData(leftPartFormData);

		leftPart.setLayout(new FormLayout());	
		
		/*Button saveMaze = new Button(leftPart, SWT.CENTER);
		saveMaze.setText("Save Maze");
		
		FormData saveMazeFormData = new FormData();
		saveMazeFormData.top = new FormAttachment(0, 0);
		saveMazeFormData.left = new FormAttachment(0, 0);
		saveMazeFormData.right = new FormAttachment(100, 0);
		saveMaze.setLayoutData(saveMazeFormData);
		
		saveMaze.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//gameCommand.
				saveGameDialog a = new saveGameDialog(dialog, "Save Game");
				a.open("pic/save.png");

			}	
		});	*/
	

		Button solve = new Button(leftPart, SWT.CENTER);
		solve.setText("Solve Maze");
		
		FormData solveFormData = new FormData();
		solveFormData.top = new FormAttachment(0, 7);
		solveFormData.left = new FormAttachment(0, 0);
		solveFormData.right = new FormAttachment(100, 0);
		solve.setLayoutData(solveFormData);
		
		solve.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gameCommand.pause();
				solve.setEnabled(false);
				//saveMaze.setEnabled(false);
				gameCommand.sloveGame();
			}	
		});
		
		Button close = new Button(leftPart, SWT.CENTER);
		close.setText("Close");
		
		FormData closeFormData = new FormData();
		closeFormData.top = new FormAttachment(95, 0);
		closeFormData.left = new FormAttachment(0, 0);
		closeFormData.right = new FormAttachment(100, 0);
		close.setLayoutData(closeFormData);
					
		close.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gameCommand.closeGame();
				dialog.dispose();	
			}			
		});
		
		Composite rightPart = new Composite(dialog, 0);

		FormData rightPartFormData = new FormData();
		rightPartFormData.top = new FormAttachment(0, 7);
		rightPartFormData.bottom = new FormAttachment(100, -7);
		rightPartFormData.left = new FormAttachment(leftPart, 7);
		rightPartFormData.right = new FormAttachment(100, -7);
		rightPart.setLayoutData(rightPartFormData);
		
		rightPart.setLayout(new FormLayout());
		
		dialog.addListener(SWT.Close, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				MessageBox messageBox = new MessageBox(dialog, SWT.APPLICATION_MODAL | SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				messageBox.setText("Exit Window");
				messageBox.setMessage("Are you sure you want to exit ?");
				if (messageBox.open() == SWT.YES) {
					gameCommand.closeGame();
					dialog.dispose();
				}
				else {
					arg0.doit=false;
				}
			}
		});
		
		gameCommand.playGame(rightPart);
	}

}
