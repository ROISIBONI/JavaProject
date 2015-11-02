/*
 * 
 */
package presenter;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Properties.
 */
@SuppressWarnings("serial")
public class Properties implements Serializable {

	/** The generate algorithm. */
	String generateAlgorithm;
	
	/** The solve algorithm. */
	String solveAlgorithm;
	
	/** The size x. */
	int sizeX;
	
	/** The size y. */
	int sizeY;
	
	/** The size z. */
	int sizeZ;
	
	/** The port. */
	int port;
	
	/** The ip. */
	String ip;

	/**
	 * Instantiates a new properties.
	 */
	public Properties() {
		this.generateAlgorithm = "DFS";
		this.solveAlgorithm = "BFS";
		this.sizeX = 1;
		this.sizeY = 8;
		this.sizeZ = 8;
		this.port = 5555;
		this.ip = "127.0.0.1";
	}
	
	/**
	 * Instantiates a new properties.
	 *
	 * @param p the p
	 */
	public Properties(Properties p) {
		this.generateAlgorithm = p.generateAlgorithm;
		this.solveAlgorithm = p.solveAlgorithm;
		this.sizeX = p.sizeX;
		this.sizeY = p.sizeY;
		this.sizeZ = p.sizeZ;
		this.port = p.port;
		this.ip = p.ip;
	}

	/**
	 * Gets the generate algorithm.
	 *
	 * @return the generate algorithm
	 */
	public String getGenerateAlgorithm() {
		return generateAlgorithm;
	}

	/**
	 * Sets the generate algorithm.
	 *
	 * @param generateAlgorithm the new generate algorithm
	 */
	public void setGenerateAlgorithm(String generateAlgorithm) {
		this.generateAlgorithm = generateAlgorithm;
	}

	/**
	 * Gets the solve algorithm.
	 *
	 * @return the solve algorithm
	 */
	public String getSolveAlgorithm() {
		return solveAlgorithm;
	}

	/**
	 * Sets the solve algorithm.
	 *
	 * @param solveAlgorithm the new solve algorithm
	 */
	public void setSolveAlgorithm(String solveAlgorithm) {
		this.solveAlgorithm = solveAlgorithm;
	}

	/**
	 * Gets the size x.
	 *
	 * @return the size x
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * Sets the size x.
	 *
	 * @param sizeX the new size x
	 */
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	/**
	 * Gets the size y.
	 *
	 * @return the size y
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * Sets the size y.
	 *
	 * @param sizeY the new size y
	 */
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	/**
	 * Gets the size z.
	 *
	 * @return the size z
	 */
	public int getSizeZ() {
		return sizeZ;
	}

	/**
	 * Sets the size z.
	 *
	 * @param sizeZ the new size z
	 */
	public void setSizeZ(int sizeZ) {
		this.sizeZ = sizeZ;
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

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

}
