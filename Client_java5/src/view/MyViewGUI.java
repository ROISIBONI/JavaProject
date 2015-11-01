package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import presenter.DataObject;
import view.View; 

// TODO: Auto-generated Javadoc
/**
 * The Class MyViewGUI.
 */
public class MyViewGUI extends BasicWindow implements View {

	/** The Constant ERROR. */
	public static final int ERROR 				=-1;
	
	/** The Constant GENERATE_GAME. */
	public static final int GENERATE_GAME		= 0;
	
	/** The Constant PLAY_GAME. */
	public static final int PLAY_GAME 			= 1;
	
	/** The Constant SOLVE_GAME. */
	public static final int SOLVE_GAME			= 2;
	
	/** The Constant DISPLAY_SOLUTION. */
	public static final int DISPLAY_SOLUTION 	= 3;
	
	/** The Constant EXIT. */
	public static final int EXIT 				= 4;
	
	/** The game command. */
	CommandGameGUI gameCommand;
	
	/**
	 * Instantiates a new my view gui.
	 *
	 * @param title the title
	 * @param width the width
	 * @param height the height
	 * @param gameCommand the game command
	 */
	public MyViewGUI(String title, int width, int height, CommandGameGUI gameCommand) {
		super(title, width, height);
		this.gameCommand = gameCommand;
		gameCommand.setObservable(this);
	}
	
	/**
	 * Instantiates a new my view gui.
	 *
	 * @param title the title
	 * @param path the path
	 * @param gameCommand the game command
	 */
	public MyViewGUI(String title, String path, CommandGameGUI gameCommand) {
		super(title, path);
		this.gameCommand = gameCommand;
		gameCommand.setObservable(this);
	}

	/* (non-Javadoc)
	 * @see view.BasicWindow#initWidgets()
	 */
	@Override
	void initWidgets() {
		
		shell.setLayout(new FormLayout());
		
		Cursor handCursor = new Cursor(display, SWT.CURSOR_HAND);
		shell.setCursor(handCursor);

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem fileMenu = new MenuItem(menu, SWT.CASCADE);
		fileMenu.setText("file");
		
		MenuItem GameMenu = new MenuItem(menu, SWT.CASCADE);
		GameMenu.setText("game");

		Menu fileSubMenu = new Menu(fileMenu);
		fileMenu.setMenu(fileSubMenu);
		
		Menu GameSubMenu = new Menu(GameMenu);
		GameMenu.setMenu(GameSubMenu);
			
		// open properties - file sub menu
		MenuItem openProperties = new MenuItem(fileSubMenu, SWT.NONE);
		openProperties.setText("Open properties");
		
		openProperties.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				FileDialog fd=new FileDialog(shell,SWT.OPEN);
				fd.setText("Open Properties");
				fd.setFilterPath("");
				String[] filterExt = { "*.xml" };
				fd.setFilterExtensions(filterExt);
				String filePath = fd.open();	
				gameCommand.openProperties(filePath);
			}
		});
		
		
		// change properties  - file sub menu
		MenuItem changeProperties = new MenuItem(fileSubMenu, SWT.NONE);
		changeProperties.setText("Change properties");
		
		changeProperties.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PropertiesDialog propertiesDialog = new PropertiesDialog(shell,"Properties");
				propertiesDialog.setProperties(gameCommand.getProperties());
				propertiesDialog.open("pic/DialogWindow.png");
				if (propertiesDialog.getFlag()) {
					gameCommand.setProperties(propertiesDialog.getProperties());
				}
			}
		});

		// exit - file sub menu
		MenuItem exit = new MenuItem(fileSubMenu, SWT.NONE);
		exit.setText("Exit");
		
		SelectionListener selectionExitListener = new SelectionAdapter (){
			public void widgetSelected(SelectionEvent event) {			
				gameCommand.exit();
			};
		};
		exit.addSelectionListener(selectionExitListener);
		
		ShellListener ShellExitListener = new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent arg0) {
				gameCommand.exit();
				super.shellClosed(arg0);
			}
		};
		shell.addShellListener(ShellExitListener);

		
		// start new game - game sub menu
		MenuItem startGameMenu = new MenuItem(GameSubMenu, SWT.PUSH);
		startGameMenu.setText("Start new game");
		
		// load game - game sub menu
		/*MenuItem loadGame = new MenuItem(GameSubMenu, SWT.PUSH);
		loadGame.setText("Load game");*/
		
		// start new game - Button 
		Button startGameButton = new Button(shell, SWT.PUSH);
		startGameButton.setText("Start New Game");
		startGameButton.setFont(new Font(display, new FontData("Cooper Black", 18, SWT.None)));
		
		FormData startGameButtonFromData = new FormData();
		startGameButtonFromData.top = new FormAttachment(45, 0);
		startGameButtonFromData.left = new FormAttachment(50, -140);
		startGameButtonFromData.right = new FormAttachment(50, 140);
		startGameButton.setLayoutData(startGameButtonFromData);

		SelectionListener startNewGameListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {			
				gameCommand.generateGame();
			};
		};
	
		startGameMenu.addSelectionListener(startNewGameListener);
		startGameButton.addSelectionListener(startNewGameListener);
	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start() {
		run();
	}

	/* (non-Javadoc)
	 * @see view.View#display(presenter.DataObject)
	 */
	@Override
	public void display(DataObject obj) {
		
		if (obj.getSerialNumber() == ERROR) {
			
			if (obj.getData() instanceof String) {
				display.syncExec(new Runnable() {
					@Override
					public void run() {
						String text = (String) obj.getData();
						MessageBox messageBox = new MessageBox(shell.getShell(), SWT.ICON_WARNING );
						messageBox.setText("Error Command");
						messageBox.setMessage(text);
						messageBox.open();	
					}
				});
			}
		}
		else if (obj.getSerialNumber() == GENERATE_GAME) {
			gameCommand.getGameData();
		}
		else if (obj.getSerialNumber() == PLAY_GAME) {
			shell.getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					if (gameCommand.setGameData(obj.getData())) {
						GameDialog gameDialog = new GameDialog(shell,gameCommand.getGameName(),gameCommand);
						gameDialog.open(1000, 640);
					}
				}
			
			});
		}
		else if (obj.getSerialNumber() == SOLVE_GAME) {
			gameCommand.getSolutionData();
		}	
		else if (obj.getSerialNumber() == DISPLAY_SOLUTION) {
			gameCommand.displaySloveGame(obj.getData());
		}	
		else if (obj.getSerialNumber() == EXIT) {
			exit();
		}	
	}

	/* (non-Javadoc)
	 * @see view.View#exit()
	 */
	@Override
	public void exit() {
		display.dispose();
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	@Override
	public void notifyObservers(Object obj) {
		setChanged();
		super.notifyObservers(obj);
	}
}
