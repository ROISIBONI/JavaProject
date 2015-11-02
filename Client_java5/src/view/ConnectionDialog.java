/*
 * 
 */
package view;

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
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import presenter.ManagerProperties;
import presenter.Properties;


// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionDialog.
 */
public class ConnectionDialog extends BasicDialog {

	/** The properties. */
	Properties properties;
	
	/** The ip_a. */
	int ip_a; // X.0.0.0
	
	/** The ip_b. */
	int ip_b; // 127.x.0.0
	
	/** The ip_c. */
	int ip_c; // 127.0.x.0
	
	/** The ip_d. */
	int ip_d; // 127.0.0.x
	
	/** The port. */
	int port;

	/**
	 * Instantiates a new connection dialog.
	 *
	 * @param shell the shell
	 * @param text the text
	 */
	public ConnectionDialog(Shell shell, String text) {
		super(shell, text);
		properties = new Properties();
		setIp(properties.getIp());
		this.port = properties.getPort();
		
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
		this.port = properties.getPort();
		setIp(properties.getIp());
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip_a+"."+ip_b+"."+ip_c+"."+ip_d;
	}
	
	/**
	 * Sets the ip.
	 *
	 * @param ip the new ip
	 */
	public void setIp(String ip) {
		String [] arrIP = ip.split("\\.");
		this.ip_a = Integer.parseInt(arrIP[0]);
		this.ip_b = Integer.parseInt(arrIP[1]);
		this.ip_c = Integer.parseInt(arrIP[2]);
		this.ip_d = Integer.parseInt(arrIP[3]);	
	}
	
	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see view.BasicDialog#initWidgets(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	void initWidgets(Shell dialog) {

		dialog.setLayout(new FormLayout());

		Cursor handCursor = new Cursor(dialog.getDisplay(), SWT.CURSOR_HAND);
		dialog.setCursor(handCursor);

		Font fontText = new Font(dialog.getDisplay(), new FontData("Cooper Black", 25, SWT.None));
		Color color = new Color(dialog.getDisplay(), 255, 255, 255, 0);

		RowLayout horizontal = new RowLayout();
		RowLayout vertical = new RowLayout();
		vertical.type = SWT.VERTICAL;

		// title
		Text title = new Text(dialog, SWT.CENTER | SWT.READ_ONLY);
		title.setText("Connection");
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
		propertiesNameFormData.top = new FormAttachment(30, 0);
		propertiesNameFormData.left = new FormAttachment(5, 0);
		propertiesName.setLayoutData(propertiesNameFormData);

		propertiesName.setLayout(horizontal);

		Label propertiesText = new Label(propertiesName, SWT.NONE);
		propertiesText.setText("Properties file name:");
		Text propertiesBox = new Text(propertiesName, SWT.NONE);
		propertiesBox.setText("My File");
		propertiesBox.setTextLimit(10);
		
		
		// ip 
		Composite ipComposite = new Composite(dialog, 0);
		ipComposite.setBackground(color);

		FormData ipCompositeFormData = new FormData();
		ipCompositeFormData.top = new FormAttachment(50, 0);
		ipCompositeFormData.left = new FormAttachment(5, 0);
		ipComposite.setLayoutData(ipCompositeFormData);

		ipComposite.setLayout(horizontal);

		Label ipText = new Label(ipComposite, SWT.NONE);
		ipText.setText("IP: ");
		
		Spinner a = new Spinner(ipComposite, SWT.None);
		a.setMinimum(1);
		a.setMaximum(255);
		a.setSelection(ip_a);

		Spinner b = new Spinner(ipComposite, SWT.None);
		b.setMinimum(0);
		b.setMaximum(255);
		b.setSelection(ip_b);

		Spinner c = new Spinner(ipComposite, SWT.None);
		c.setMinimum(0);
		c.setMaximum(255);
		c.setSelection(ip_c);
		
		Spinner d = new Spinner(ipComposite, SWT.None);
		d.setMinimum(0);
		d.setMaximum(255);
		d.setSelection(ip_d);

		a.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				ip_a = a.getSelection();
			};
		});
		b.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				ip_b = b.getSelection();
			};
		});
		c.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				ip_c = c.getSelection();
			};
		});
		d.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				ip_d = d.getSelection();
			};
		});
		
		// port
		Composite portComposite = new Composite(dialog, 0);
		portComposite.setBackground(color);

		FormData portCompositeFormData = new FormData();
		portCompositeFormData.top = new FormAttachment(70, 0);
		portCompositeFormData.left = new FormAttachment(5, 0);
		portComposite.setLayoutData(portCompositeFormData);

		portComposite.setLayout(horizontal);

		Label portText = new Label(portComposite, SWT.NONE);
		portText.setText("PORT: ");
		
		Spinner portSpinner = new Spinner(portComposite, SWT.None);
		portSpinner.setMinimum(2000);
		portSpinner.setMaximum(9000);
		portSpinner.setSelection(port);


		portSpinner.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				port = portSpinner.getSelection();
			};
		});


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

				properties.setPort(port);
				properties.setIp(getIp());
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
