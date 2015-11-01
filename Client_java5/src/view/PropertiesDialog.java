package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import presenter.ManagerProperties;
import presenter.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertiesDialog.
 */
public class PropertiesDialog extends BasicDialog {

	/** The properties. */
	Properties properties;
	
	/** The flag. */
	Boolean flag = false;

	/**
	 * Instantiates a new properties dialog.
	 *
	 * @param shell the shell
	 * @param text the text
	 */
	public PropertiesDialog(Shell shell, String text) {
		super(shell, text);
		properties = new Properties();
	}

	/**
	 * Gets the flag.
	 *
	 * @return the flag
	 */
	public Boolean getFlag() {
		return flag;
	}

	/**
	 * Sets the flag.
	 *
	 * @param flag the new flag
	 */
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * Sets the properties.
	 *
	 * @param properties the new properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/* (non-Javadoc)
	 * @see view.BasicDialog#initWidgets(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	void initWidgets(Shell dialog) {

		dialog.setLayout(new FormLayout());

		Cursor handCursor = new Cursor(dialog.getDisplay(), SWT.CURSOR_HAND);
		dialog.setCursor(handCursor);

		Font font = new Font(dialog.getDisplay(), new FontData("ariel", 10, SWT.None));
		Font fontText = new Font(dialog.getDisplay(), new FontData("Cooper Black", 25, SWT.None));
		Color color = new Color(dialog.getDisplay(), 255, 255, 255, 0);

		RowLayout horizontal = new RowLayout();
		RowLayout vertical = new RowLayout();
		vertical.type = SWT.VERTICAL;

		// title
		Text title = new Text(dialog, SWT.CENTER | SWT.READ_ONLY);
		title.setText("Settings");
		title.setFont(fontText);
		title.setBackground(color);

		FormData titleFromData = new FormData();
		titleFromData.top = new FormAttachment(7, 0);
		titleFromData.left = new FormAttachment(5, 0);
		titleFromData.right = new FormAttachment(95, 0);
		title.setLayoutData(titleFromData);

		// properties name
		Composite propertiesName = new Composite(dialog, 0);
		propertiesName.setBackground(color);

		FormData propertiesNameFormData = new FormData();
		propertiesNameFormData.top = new FormAttachment(20, 0);
		propertiesNameFormData.left = new FormAttachment(5, 0);
		propertiesName.setLayoutData(propertiesNameFormData);

		propertiesName.setLayout(horizontal);

		Label propertiesText = new Label(propertiesName, SWT.NONE);
		propertiesText.setText("Properties file name:");
		Text propertiesBox = new Text(propertiesName, SWT.NONE);
		propertiesBox.setText("My File");
		propertiesBox.setTextLimit(10);

		// generator algorithm
		Group generatorAlgorithm = new Group(dialog, SWT.None);
		generatorAlgorithm.setText("generator algorithm");
		generatorAlgorithm.setFont(font);

		FormData generatorAlgorithmFormData = new FormData();
		generatorAlgorithmFormData.top = new FormAttachment(32, 0);
		generatorAlgorithmFormData.left = new FormAttachment(5, 0);
		generatorAlgorithm.setLayoutData(generatorAlgorithmFormData);

		generatorAlgorithm.setLayout(vertical);

		Button dfs = new Button(generatorAlgorithm, SWT.RADIO);
		dfs.setText("DFS");

		Button prim = new Button(generatorAlgorithm, SWT.RADIO);
		prim.setText("Prim");

		Button simple = new Button(generatorAlgorithm, SWT.RADIO);
		simple.setText("Simple");
		
		if (properties.getGenerateAlgorithm().toLowerCase().equals(simple.getText().toLowerCase())) {
			simple.setSelection(true);
		} else if (properties.getGenerateAlgorithm().toLowerCase().equals(prim.getText().toLowerCase())) {
			prim.setSelection(true);
		} else {
			dfs.setSelection(true);
		}

		SelectionListener selectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Button button = ((Button) event.widget);
				if (button.getSelection()) {
					properties.setGenerateAlgorithm(button.getText());
				}
			};
		};
		dfs.addSelectionListener(selectionListener);
		prim.addSelectionListener(selectionListener);
		simple.addSelectionListener(selectionListener);

		// dimensions
		Group dimensions = new Group(dialog, SWT.None);
		dimensions.setText("maze dimensions x,y,z");
		dimensions.setFont(font);

		FormData dimensionsFormData = new FormData();
		dimensionsFormData.top = new FormAttachment(60, 0);
		dimensionsFormData.left = new FormAttachment(5, 0);
		dimensions.setLayoutData(dimensionsFormData);

		dimensions.setLayout(horizontal);

		Spinner x = new Spinner(dimensions, SWT.None);
		x.setMinimum(1);
		x.setMaximum(20);
		x.setSelection(properties.getSizeX());

		Spinner y = new Spinner(dimensions, SWT.None);
		y.setMinimum(1);
		y.setMaximum(50);
		y.setSelection(properties.getSizeY());

		Spinner z = new Spinner(dimensions, SWT.None);
		z.setMinimum(1);
		z.setMaximum(50);
		z.setSelection(properties.getSizeZ());

		x.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				properties.setSizeX(x.getSelection());
			};
		});
		y.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				properties.setSizeY(y.getSelection());
			};
		});
		z.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				properties.setSizeZ(z.getSelection());
			};
		});

		// solution algorithm
		Group solutionAlgorithm = new Group(dialog, SWT.None);
		solutionAlgorithm.setText("solution algorithm");
		solutionAlgorithm.setFont(font);

		FormData solutionAlgorithmFormData = new FormData();
		solutionAlgorithmFormData.top = new FormAttachment(32, 0);
		solutionAlgorithmFormData.left = new FormAttachment(50, 0);
		solutionAlgorithm.setLayoutData(solutionAlgorithmFormData);

		solutionAlgorithm.setLayout(vertical);

		Button bfs = new Button(solutionAlgorithm, SWT.RADIO);
		bfs.setText("BFS");

		Button astartAirDistance = new Button(solutionAlgorithm, SWT.RADIO);
		astartAirDistance.setText("Astar Air-Distance");

		Button astartManhattenDistanc = new Button(solutionAlgorithm, SWT.RADIO);
		astartManhattenDistanc.setText("Astar Manhatten-Distance");

		if (properties.getSolveAlgorithm().toLowerCase().equals(astartManhattenDistanc.getText().toLowerCase())) {
			astartManhattenDistanc.setSelection(true);
		} else if (properties.getSolveAlgorithm().toLowerCase().equals(astartAirDistance.getText().toLowerCase())) {
			astartAirDistance.setSelection(true);
		} else {
			bfs.setSelection(true);
		}

		SelectionListener solutionAlgorithmListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Button button = ((Button) event.widget);
				if (button.getSelection()) {
					properties.setSolveAlgorithm(button.getText());
				}
			};
		};
		bfs.addSelectionListener(solutionAlgorithmListener);
		astartManhattenDistanc.addSelectionListener(solutionAlgorithmListener);
		astartAirDistance.addSelectionListener(solutionAlgorithmListener);

		// buttons
		Composite buttons = new Composite(dialog, 0);
		buttons.setBackground(color);

		FormData compositeFormData = new FormData();
		compositeFormData.top = new FormAttachment(85, 0);
		compositeFormData.left = new FormAttachment(74, 0);
		buttons.setLayoutData(compositeFormData);

		buttons.setLayout(horizontal);



		Button save = new Button(buttons, SWT.PUSH);
		save.setText("save");
		save.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent event) {
				flag=true;
				if (propertiesBox.getText() == "") {
					propertiesBox.setText("My File");
				}
				try {
					ManagerProperties manager = new ManagerProperties();
					manager.saveFile(propertiesBox.getText(), properties);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dialog.close();
			}
		});
		
		Button cancel = new Button(buttons, SWT.PUSH);
		cancel.setText("cancel");
		cancel.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				dialog.close();
			};
		});
	}
}
