package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// TODO: Auto-generated Javadoc
/**
 * The Class MyServer.
 */
public class MyServer {
	
	/** The server. */
	ServerSocket server;
	
	/** The clients. */
	HashMap<String, Socket> clients;
	
	/** The clinet handler. */
	ClinetHandler clinetHandler;
	
	/** The threadpool. */
	ExecutorService threadpool;
	
	/** The server running. */
	volatile boolean serverRunning;
	
	/** The main server thread. */
	Thread mainServerThread;
	
	/** The clients handled. */
	int clientsHandled = 0;
	
	Properties properties;
	
	/**
	 * Instantiates a new my server.
	 *
	 * @param port the port
	 * @param clinetHandler the clinet handler
	 * @param numOfClients the num of clients
	 */
	public MyServer(Properties properties, ClinetHandler clinetHandler) {
		this.properties = properties;
		this.clinetHandler = clinetHandler;
		this.clients = new HashMap<String, Socket> ();
		this.serverRunning= false;
	}
	
	/**
	 * Gets the clients.
	 *
	 * @return the clients
	 */
	public String[] getClients() {
		String[] allClients = new String[clients.size()];
		Set<String> hashSetString = clients.keySet();
		hashSetString.toArray(allClients);
		return allClients;
	}

	/**
	 * Checks if is server running.
	 *
	 * @return true, if is server running
	 */
	public boolean isServerRunning() {
		return serverRunning;
	}

	/**
	 * Adds the client.
	 *
	 * @param socket the socket
	 */
	private synchronized void addClient(Socket socket) {
		this.clients.put(socket.getInetAddress().toString(), socket);
	}

	/**
	 * Removes the client.
	 *
	 * @param ip the ip
	 */
	public synchronized void removeClient(String ip) {
		this.clients.remove(ip);
	}

	/**
	 * Start.
	 *
	 * @throws Exception the exception
	 */
	public void start() throws Exception {
		server = new ServerSocket(properties.getPort());
		server.setSoTimeout(10 * 1000);
		threadpool = Executors.newFixedThreadPool(properties.getNumOfClient());
		serverRunning = true;

		mainServerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (serverRunning) {
					try {
						final Socket someClient = server.accept();
						if (someClient != null) {
							addClient(someClient);
							threadpool.execute(new Runnable() {
								@Override
								public void run() {
									try {
										clientsHandled++;
										someClient.setSoTimeout(10*1000);
										clinetHandler.handleClient(someClient.getInputStream(),someClient.getOutputStream());
										removeClient(someClient.getInetAddress().toString());
										someClient.close();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							});
						}
					} catch (SocketTimeoutException e) {
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} // end of the mainServerThread task
		});
		mainServerThread.start();
	}

	/**
	 * Close.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unused")
	public void close() throws Exception {
		if (serverRunning==true) {
			serverRunning = false;
			// do not execute jobs in queue, continue to execute running threads
			threadpool.shutdown();
			// wait 10 seconds over and over again until all running jobs have
			// finished
			boolean allTasksCompleted = false;
			while (!(allTasksCompleted = threadpool.awaitTermination(10, TimeUnit.SECONDS)));
			clinetHandler.close();

			mainServerThread.join();

			server.close();
		}
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	
}
