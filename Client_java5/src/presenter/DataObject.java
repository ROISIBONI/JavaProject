/*
 * 
 */
package presenter;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class DataObject.
 */
@SuppressWarnings("serial")
public class DataObject implements Serializable {

	/** The serial number. */
	private int serialNumber;
	
	/** The data. */
	private Object data;

	/**
	 * Instantiates a new data object.
	 *
	 * @param serialNumber the serial number
	 * @param data the data
	 */
	public DataObject(int serialNumber, Object data) {
		this.serialNumber = serialNumber;
		this.data = data;
	}

	/**
	 * Gets the serial number.
	 *
	 * @return the serial number
	 */
	public int getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Sets the serial number.
	 *
	 * @param serialNumber the new serial number
	 */
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Object data) {
		this.data = data;
	}

}