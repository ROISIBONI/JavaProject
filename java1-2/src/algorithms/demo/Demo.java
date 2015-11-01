package algorithms.demo;

import algorithms.mazeGenerators.DFS1Maze3dGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.PrimMaze3dGenerator;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;

/**
 * The Class Demo.
 */
public class Demo {
	
	public static void run () {
		
		Maze3dGenerator mazeDFS1 = new DFS1Maze3dGenerator();
		//Maze3dGenerator mazeDFS2 = new DFS2Maze3dGenerator();
		Maze3dGenerator mazePrim = new PrimMaze3dGenerator();
		//Maze3dGenerator mazeSimple = new SimpleMaze3dGenerator();

		Maze3d maze1 = mazeDFS1.generate(3, 10, 10);
		//Maze3d maze2 = mazeDFS2.generate(3, 10, 10);
		Maze3d maze3 = mazePrim.generate(3, 10, 10);
		//Maze3d maze4 = mazeSimple.generate(3, 10, 10);
		
		Searchable<Position> mazeGame1 = new Maze3dSearchble (maze1);
		//Searchable<Position> mazeGame2 = new Maze3dSearchble (maze2);
		Searchable<Position> mazeGame3 = new Maze3dSearchble (maze3);
		//Searchable<Position> mazeGame4 = new Maze3dSearchble (maze4);
		
		Searcher<Position> bfs = new BFS<Position>();
		Searcher<Position> Astar_AirDistance = new Astar<Position>(new MazeAirDistance());
		Searcher<Position> Astar_ManhattanDistance = new Astar<Position>(new MazeManhattanDistance());
		
		Solution<Position> a = null;
		Solution<Position> b = null;
		Solution<Position> c = null;
		
		
		// ------------ DFS 1 -----------------
		System.out.println("maze - DFS 1:\n");
		System.out.println("start- "+mazeGame1.getStartState().toString());
		System.out.println("goal- "+mazeGame1.getGoalState().toString()+"\n");
		maze1.print();
		a=bfs.search(mazeGame1);
		b=Astar_AirDistance.search(mazeGame1);
		c=Astar_ManhattanDistance.search(mazeGame1);
		System.out.println("Solution BFS:");
		a.print();
		System.out.println("Evaluated Nodes BFS: "+ bfs.getNumberOfNodesEvaluated()+"\n");
		((CommonSearcher<Position>) bfs).setEvaluatedNodes(0);
		System.out.println("Solution Astar AirDistance:");
		b.print();
		System.out.println("Evaluated Nodes Astar AirDistance: "+ Astar_AirDistance.getNumberOfNodesEvaluated()+"\n");
		((CommonSearcher<Position>) Astar_AirDistance).setEvaluatedNodes(0);
		System.out.println("Solution Astar ManhattanDistance:");
		c.print();
		System.out.println("Evaluated Nodes Astar AirDistance: "+ Astar_ManhattanDistance.getNumberOfNodesEvaluated()+"\n");
		((CommonSearcher<Position>) Astar_ManhattanDistance).setEvaluatedNodes(0);
		
		
		// ------------ Prim ----------------
		System.out.println("maze - Prim:\n");
		System.out.println("start- "+mazeGame3.getStartState().toString());
		System.out.println("goal- "+mazeGame3.getGoalState().toString()+"\n");
		maze3.print();
		a=bfs.search(mazeGame3);
		b=Astar_AirDistance.search(mazeGame3);
		c=Astar_ManhattanDistance.search(mazeGame3);
		System.out.println("Solution BFS:");
		a.print();
		System.out.println("Evaluated Nodes BFS: "+ bfs.getNumberOfNodesEvaluated()+"\n");
		((CommonSearcher<Position>) bfs).setEvaluatedNodes(0);
		System.out.println("Solution Astar AirDistance:");
		b.print();
		System.out.println("Evaluated Nodes Astar AirDistance: "+ Astar_AirDistance.getNumberOfNodesEvaluated()+"\n");
		((CommonSearcher<Position>) Astar_AirDistance).setEvaluatedNodes(0);
		System.out.println("Solution Astar ManhattanDistance:");
		c.print();
		System.out.println("Evaluated Nodes Astar AirDistance: "+ Astar_ManhattanDistance.getNumberOfNodesEvaluated()+"\n");
		((CommonSearcher<Position>) Astar_ManhattanDistance).setEvaluatedNodes(0);
	}
}
