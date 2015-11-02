package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Properties implements Serializable {

	int port;
	int numOfClient;

	public Properties() {
		this.port = 5555;
		this.numOfClient = 3;
	}
	
	public Properties(int port,int numOfClient) {
		this.port = port;
		this.numOfClient = numOfClient;
	}
	

	public Properties(Properties p) {
		this.port = p.port;
		this.numOfClient = p.numOfClient;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getNumOfClient() {
		return numOfClient;
	}

	public void setNumOfClient(int numOfClient) {
		this.numOfClient = numOfClient;
	}

}
