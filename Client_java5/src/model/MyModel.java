package model;

import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import presenter.DataObject;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

// TODO: Auto-generated Javadoc
/**
 * The Class MyModel.
 */
public class MyModel extends Observable implements Model {

	/** The Constant ERORR. */
	private static final int ERORR		 		= -1;
	
	/** The Constant GENERATE_GAME. */
	private static final int GENERATE_GAME 		= 1;
	
	/** The Constant SOLVE_GAME. */
	private static final int SOLVE_GAME			= 2;
	
	/** The Constant PORT. */
	public static final int PORT 				= 5555;
	
	/** The Constant IP. */
	public static final String IP				= "127.0.0.1";

	
	/** The maze3d. */
	private HashMap<String, Maze3d> maze3d;
	
	/** The solution. */
	private HashMap<String, Solution<Position>> solution;
	
	/** The executor. */
	private ExecutorService executor;

	/**
	 * Instantiates a new my model.
	 *
	 * @param numOfThreads the num of threads
	 */
	public MyModel(int numOfThreads) {

		maze3d = new HashMap<String, Maze3d>();
		solution = new HashMap<String, Solution<Position>>();

		if (numOfThreads > 1) {
			executor = Executors.newFixedThreadPool(numOfThreads);
		} else {
			executor = Executors.newFixedThreadPool(3);
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#generate3dMaze(presenter.DataObject)
	 */
	@Override
	public void generate3dMaze(DataObject obj) {
		
		this.executor.submit(new Runnable() {
			@Override
			public void run() {

				String allParam = (String) obj.getData();

				String[] param = allParam.split(","); // maze name, type maze, x, y, z
				
				if (param != null && param.length == 5 && param[0]!="") {
					if (!maze3d.containsKey(param[0])) {
						String mazeName = null;
						String algorithm = null;
						int x = 0;
						int y = 0;
						int z = 0;
						try {
							mazeName = param[0];
							algorithm = param[1];
							x = Integer.parseInt(param[2]);
							y = Integer.parseInt(param[3]);
							z = Integer.valueOf(param[4]);					
						} catch (Exception e) {
							obj.setData("invalid parameters type");
							obj.setSerialNumber(ERORR);
							notifyObservers(obj);
						}
						Socket myServer;
						try {
							myServer = new Socket(IP, PORT);
							myServer.setSoTimeout(10*1000);
						} catch (Exception e) {
							obj.setData("problem connecting to Server");
							obj.setSerialNumber(ERORR);
							notifyObservers(obj);
							return;
						}
						try {
							ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(myServer.getOutputStream()));
							output.writeObject(GENERATE_GAME);
							output.writeObject(algorithm);
							output.writeObject(x);
							output.writeObject(y);
							output.writeObject(z);
							output.flush();
	
							ObjectInputStream input=new ObjectInputStream(new BufferedInputStream(myServer.getInputStream()));	
							DataObject o = (DataObject)input.readObject();
							if (o.getSerialNumber()==(ERORR)) {
								o.setData("invalid parameters");
								notifyObservers(o);
							}
							else {
								maze3d.put(mazeName, (Maze3d) o.getData());
								obj.setData("maze " + mazeName + " is ready");
								notifyObservers(obj);
							}
							
							output.close();
							input.close();
							
							myServer.close();								
						} catch (Exception e) {
							obj.setData("problem with stream");
							notifyObservers(obj);
						}

					}
					else {
						obj.setData("maze name is taken");
						obj.setSerialNumber(ERORR);
						notifyObservers(obj);
					}
					
				}
				else {
					obj.setData("invalid number of parameters");
					obj.setSerialNumber(ERORR);
					notifyObservers(obj);
				}
			}
		});
	}

	/* (non-Javadoc)
	 * @see model.Model#exit()
	 */
	public void exit() {
		
		executor.shutdown();
	}

	/* (non-Javadoc)
	 * @see model.Model#displayMaze(presenter.DataObject)
	 */
	@Override
	public void displayMaze(DataObject obj) {

		String mazeName = (String) obj.getData();

		if (mazeName == null) {
			obj.setData("invalid parameter - null");
			obj.setSerialNumber(ERORR);
			notifyObservers(obj);
		}

		else {

			Maze3d m = maze3d.get(mazeName);

			if (m == null) {
				obj.setData("there is no maze named - " + mazeName);
				obj.setSerialNumber(ERORR);
				notifyObservers(obj);
			}

			else {
				obj.setData(m);
				notifyObservers(obj);
			}
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#displayCrossSection(presenter.DataObject)
	 */
	@Override
	public void displayCrossSection(DataObject obj) {

		String nameAndCross = (String) obj.getData();

		if (nameAndCross != null) {

			String[] param = nameAndCross.split(","); // name maze, x\y\z, num

			if (param.length != 3) {
				obj.setData("invalid number of parameters");
				obj.setSerialNumber(ERORR);
				notifyObservers(obj);
			}

			else {

				Maze3d m = maze3d.get(param[0]);

				if (m == null) {
					obj.setData("there is no maze named - " + param[0]);
					obj.setSerialNumber(ERORR);
					notifyObservers(obj);
				}

				else {

					try {

						switch (param[1].toLowerCase()) {
						case "x":
							obj.setData(m.getCrossSectionByX(Integer.parseInt(param[2])));
							notifyObservers(obj);
							break;
						case "y":
							obj.setData(m.getCrossSectionByY(Integer.parseInt(param[2])));
							notifyObservers(obj);
							break;
						case "z":
							obj.setData(m.getCrossSectionByZ(Integer.parseInt(param[2])));
							notifyObservers(obj);
							break;
						default:
							obj.setData("invalid axis (x/y/z)");
							obj.setSerialNumber(ERORR);
							notifyObservers(obj);
							break;
						}
					}

					catch (Exception e) {
						obj.setData("invalid index");
						obj.setSerialNumber(ERORR);
						notifyObservers(obj);
					}
				}
			}
		}

		else {
			obj.setData("invalid parameter - null");
			obj.setSerialNumber(ERORR);
			notifyObservers(obj);
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#saveMaze(presenter.DataObject)
	 */
	@Override
	public void saveMaze(DataObject obj) {

		String mazeNameAndFileName = (String) obj.getData();

		if (mazeNameAndFileName == null) {
			obj.setData("invalid parameter - null");
			obj.setSerialNumber(ERORR);
			notifyObservers(obj);
		}

		else {

			String[] param = mazeNameAndFileName.split(",");

			if (param.length != 2) {
				obj.setData("invalid number of parameters");
				obj.setSerialNumber(ERORR);
				notifyObservers(obj);
			}

			else {

				Maze3d m = maze3d.get(param[0]);

				if (m == null) {
					obj.setData("invalid parameter - null");
					obj.setSerialNumber(ERORR);
					notifyObservers(obj);
				}

				else {

					try {
						OutputStream compressor = new MyCompressorOutputStream(new FileOutputStream(param[1]));
						compressor.write(m.toByteArray().length);
						compressor.write(m.toByteArray());
						compressor.close();
						obj.setData("the maze was saved");
						notifyObservers(obj);
					}

					catch (IOException e) {
						e.printStackTrace();
						obj.setData("maze not save - compressor problem");
						obj.setSerialNumber(ERORR);
						notifyObservers(obj);
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#loadMaze(presenter.DataObject)
	 */
	@Override
	public void loadMaze(DataObject obj) {

		String FileNameAndmazeName = (String) obj.getData();

		if (FileNameAndmazeName == null) {
			obj.setData("invalid parameter - null");
			obj.setSerialNumber(ERORR);
			notifyObservers();
		}

		else {

			String[] param = FileNameAndmazeName.split(",");

			if (param.length != 2) {
				obj.setData("invalid number of parameters");
				obj.setSerialNumber(ERORR);
				notifyObservers(obj);
			}

			else {

				try {
					InputStream decompressor = new MyDecompressorInputStream(new FileInputStream(param[0]));
					int size = decompressor.read();
					byte[] maze = new byte[size];
					decompressor.read(maze);
					decompressor.close();
					Maze3d m = new Maze3d(maze);
					maze3d.put(param[1], m);
					obj.setData("the maze was loaded");
					notifyObservers(obj);

				} catch (IOException e) {
					e.printStackTrace();
					obj.setData("maze not load - problem decompressor");
					obj.setSerialNumber(ERORR);
					notifyObservers(obj);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#mazeSize(presenter.DataObject)
	 */
	@Override
	public void mazeSize(DataObject obj) {

		String mazeName = (String) obj.getData();

		if (mazeName == null) {
			obj.setData("invalid parameter - null");
			obj.setSerialNumber(ERORR);
			notifyObservers(obj);
		}

		else {

			Maze3d m = maze3d.get(mazeName);

			if (m == null) {
				obj.setData("there is no such maze name");
				obj.setSerialNumber(ERORR);
				notifyObservers(obj);
			}

			else {
				obj.setData(m.toByteArray().length + " byte");
				notifyObservers(obj);
			}
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#solveMaze(presenter.DataObject)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void solveMaze(DataObject obj) {

		this.executor.submit(new Runnable() {
			
			@Override
			public void run() {
				
				String data = (String) obj.getData();
				
				if (data == null ) {
					obj.setData("invalid parameters - null");
					obj.setSerialNumber(ERORR);
					notifyObservers(obj);
				}
				else {

					String[] param = data.split(",");
					
					Maze3d m = maze3d.get(param[0]);

					if (m == null) {
						obj.setData("invalid maze name");
						obj.setSerialNumber(ERORR);
						notifyObservers(obj);
					}
					else {
						
						boolean startEqualCurrent = true;
						
						String mazeName = param[0];
						
						switch (param.length) {
						case 3:
							String[] numberPosition = param[2].split(" ");
							Position current = null;
							if (numberPosition.length==3) {
								try {
									current = new Position(Integer.valueOf(numberPosition[0]),Integer.valueOf(numberPosition[1]),Integer.valueOf(numberPosition[2]));
									if (!m.valid(current) || m.getValue(current)==1) {
										obj.setData("invalid current position");
										obj.setSerialNumber(ERORR);
										notifyObservers(obj);
										return;
									}
								} catch (Exception e) {
									obj.setData("invalid parameters type");
									obj.setSerialNumber(ERORR);
									notifyObservers(obj);
									return;
								}	
							}
							else {
								obj.setData("invalid parameter");
								obj.setSerialNumber(ERORR);
								notifyObservers(obj);
								return;
							}
							if (!current.equals(m.getStartPosition())) {
								m = new Maze3d(m);
								m.setStartPosition(current);
								startEqualCurrent=false;
							}
							
						case 2:
							
							if (startEqualCurrent && solution.containsKey(m)) {
								obj.setData("solution arleay exist");
								notifyObservers(obj);
								return;
							}
							
							String algorithm = param[1];
							
							Socket myServer;
							try {
								myServer = new Socket(IP, PORT);
								myServer.setSoTimeout(10*1000);
							} catch (Exception e) {
								obj.setData("problem connecting to Server");
								obj.setSerialNumber(ERORR);
								notifyObservers(obj);
								return;
							}
							try {
								ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(myServer.getOutputStream()));
								output.writeObject(SOLVE_GAME);
								output.writeObject(m);
								output.writeObject(algorithm);
								output.flush();

								ObjectInputStream input=new ObjectInputStream(new BufferedInputStream(myServer.getInputStream()));	
								DataObject o = (DataObject)input.readObject();
								if (o.getSerialNumber()==(ERORR)) {
									o.setData("invalid parameters");
									notifyObservers(o);
								}
								else {
									if (param.length==3) {
										solution.put(mazeName+"*",(Solution<Position>) o.getData());
										obj.setData("solution for " + mazeName + " is ready");
									}
									else {
										solution.put(mazeName,(Solution<Position>) o.getData());
									}
									obj.setData("maze " + mazeName + " is ready");
									notifyObservers(obj);
								}
								output.close();
								input.close();
								
								myServer.close();								
							} catch (Exception e) {
								obj.setData("problem with stream");
								notifyObservers(obj);
							}
							break;
						default:
							obj.setData("invalid number of parameters");
							obj.setSerialNumber(ERORR);
							notifyObservers(obj);
							break;
						}							
					}		
				}
				
			}
		}); 
	}

	/* (non-Javadoc)
	 * @see model.Model#displaySolution(presenter.DataObject)
	 */
	@Override
	public void displaySolution(DataObject obj) {

		String mazeName = (String) obj.getData();

		if (mazeName == null) {
			obj.setData("invalid parameter - null");
			obj.setSerialNumber(ERORR);
			notifyObservers(obj);
		}

		else {

			Solution<Position> s = solution.get(mazeName);

			if (s == null) {
				obj.setData("there is no solution to the maze name");
				obj.setSerialNumber(ERORR);
				notifyObservers(obj);
			}

			else {
				obj.setData(s);
				notifyObservers(obj);
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}
}
