package model;

// TODO: Auto-generated Javadoc
/**
 * The Interface Model.
 */
public interface Model {
	
	/**
	 * Start.
	 */
	public void start();

	/**
	 * On line clients.
	 */
	public void onLineClients();

	/**
	 * Close server.
	 */
	public void closeServer();
	
	public void saveProperties ();
	
	public Properties getProperties();
	public void setProperties(Properties properties);
}
