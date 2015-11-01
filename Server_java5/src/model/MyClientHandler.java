package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.demo.Maze3dSearchble;
import algorithms.mazeGenerators.DFS1Maze3dGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.PrimMaze3dGenerator;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import presenter.DataObject;

// TODO: Auto-generated Javadoc
/**
 * The Class MyClientHandler.
 */
public class MyClientHandler implements ClinetHandler{

	/** The Constant ERORR. */
	private static final int ERORR		 		= -1;
	
	/** The Constant GENERATE_GAME. */
	private static final int GENERATE_GAME 		= 1;
	
	/** The Constant SOLVE_GAME. */
	private static final int SOLVE_GAME			= 2;
	
	/** The cache. */
	private HashMap<Maze3d, Solution<Position>> cache;
	
	/**
	 * Instantiates a new my client handler.
	 */
	@SuppressWarnings("unchecked")
	public MyClientHandler() {
		try {
			FileInputStream fis = new FileInputStream("cache.gz");
			GZIPInputStream gzis = new GZIPInputStream(fis);
			BufferedInputStream bis = new BufferedInputStream(gzis);
			ObjectInputStream in = new ObjectInputStream(bis);
			Object o = in.readObject();
			cache = (HashMap<Maze3d, Solution<Position>>) o;
			in.close();
		}
		catch (IOException | ClassNotFoundException e) {
			cache = new HashMap<Maze3d, Solution<Position>>();
		}
	}
	
	/* (non-Javadoc)
	 * @see model.ClinetHandler#handleClient(java.io.InputStream, java.io.OutputStream)
	 */
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		ObjectInputStream in;
		ObjectOutputStream out;

		try {
			in = new ObjectInputStream(new BufferedInputStream(inFromClient ));
			out = new ObjectOutputStream(new BufferedOutputStream(outToClient ));
			Integer problem = (Integer)in.readObject();
			
			switch (problem) {
			case GENERATE_GAME:
				try {
					String algorithemGenerate = (String) in.readObject();
					Integer x = (Integer) in.readObject();
					Integer y = (Integer) in.readObject();
					Integer z = (Integer) in.readObject();
					out.writeObject((DataObject)generateMaze(x,y,z,algorithemGenerate));
					out.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case SOLVE_GAME: // solve,maze name,algorithm solution heuristic*,current position*
				Maze3d maze = (Maze3d) in.readObject();
				String algorithemSolve = (String) in.readObject();	
				out.writeObject(solutionMaze(maze,algorithemSolve));
				out.flush();
				break;
			default:
				break;
			}
			in.close();
			out.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Generate maze.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @param algorithemGenerate the algorithem generate
	 * @return the data object
	 */
	private DataObject generateMaze (int x,int y,int z, String algorithemGenerate){
		
		DataObject obj = new DataObject(GENERATE_GAME, null);
		
		try {
			switch (algorithemGenerate.toUpperCase()) {
			case "DFS":
				Maze3dGenerator mazeDFS1 = new DFS1Maze3dGenerator();
				obj.setData(mazeDFS1.generate(x,y,z));
				break;
			case "PRIM":
				Maze3dGenerator mazePrim = new PrimMaze3dGenerator();
				obj.setData(mazePrim.generate(x,y,z));
				break;
			case "SIMPLE":
				Maze3dGenerator mazeSimple = new SimpleMaze3dGenerator();
				obj.setData(mazeSimple.generate(x,y,z));
				break;
			default:
				obj.setData("invalid algorithm");
				obj.setSerialNumber(ERORR);
				break;
			}
		}
		catch (Exception e) {
			obj.setData("invalid parameters");
			obj.setSerialNumber(ERORR);
		}
		return obj;
	}
	
	/**
	 * Solution maze.
	 *
	 * @param maze the maze
	 * @param algorithemSolve the algorithem solve
	 * @return the data object
	 */
	private DataObject solutionMaze (Maze3d maze,String algorithemSolve){

		DataObject obj = new DataObject(SOLVE_GAME, null);
		
		if (maze == null) {
			obj.setData("maze - null");
			obj.setSerialNumber(ERORR);
			return obj;
		}
				
		Solution<Position> s;
		
		switch (algorithemSolve.toUpperCase()) {
		case "BFS":
			Searcher<Position> bfs = new BFS<Position>();
			Searchable<Position> mazeGame1 = new Maze3dSearchble(maze);
			s=bfs.search(mazeGame1);
			break;
		case "ASTAR AIR-DISTANCE":
			Searcher<Position> Astar_AirDistance = new Astar<Position>(new MazeAirDistance());
			Searchable<Position> mazeGame2 = new Maze3dSearchble(maze);
			s=Astar_AirDistance.search(mazeGame2);
			break;
		case "ASTAR MANHATTAN-DISTANCE":
			Searcher<Position> Astar_ManhattanDistance = new Astar<Position>(new MazeManhattanDistance());
			Searchable<Position> mazeGame3 = new Maze3dSearchble(maze);
			s=Astar_ManhattanDistance.search(mazeGame3);
			break;
		default:
			obj.setData("invalid algorithm");
			obj.setSerialNumber(ERORR);
			return obj;
		}	
		cache.put(maze, s);
		obj.setData(s);
		return obj;			
	}

	/* (non-Javadoc)
	 * @see model.ClinetHandler#close()
	 */
	@Override
	public void close() {
		FileOutputStream file;
		try {
			file = new FileOutputStream("cache.gz");
			GZIPOutputStream a = new GZIPOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(a);
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(cache);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
