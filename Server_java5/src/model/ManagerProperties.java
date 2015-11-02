/*
 * 
 */
package model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class ManagerProperties.
 */
public class ManagerProperties {

	/**
	 * Load file.
	 *
	 * @param filePath the file path
	 * @return the properties
	 * @throws FileNotFoundException the file not found exception
	 */
	public Properties loadFile(String filePath) throws FileNotFoundException {

		XMLDecoder xml = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));
		Properties p = (Properties) xml.readObject();
		xml.close();
		return p;
	}
	
	/**
	 * Save file.
	 *
	 * @param fileName the file name
	 * @param p the p
	 * @throws FileNotFoundException the file not found exception
	 */
	public void saveFile (String fileName, Properties p) throws FileNotFoundException  {
		
		XMLEncoder E = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName+".xml")));
		E.writeObject(p);
		E.flush();
		E.close();
	}
}
