package test;

import algorithms.mazeGenerators.DFS1Maze3dGenerator;
import algorithms.mazeGenerators.DFS2Maze3dGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.PrimMaze3dGenerator;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;

public class Run {
	private static void testMazeGenerator(Maze3dGenerator mg){
		
		// prints the time it takes the algorithm to run
		System.out.println("measure algorithm time:");
		System.out.println(mg.measureAlgorithmTime(3,8,8));
		System.out.println("");
		
		// generate another 3d maze
		Maze3d maze=mg.generate(3,8,8);
		// get the maze entrance
		Position p=maze.getStartPosition();
		// print the position
		System.out.println("start:");
		System.out.println(p); // format "{x,y,z}"
		System.out.println("");
		
		// get all the possible moves from a position
		String[] moves=maze.getPossibleMoves(p);
		// print the moves
		System.out.println("move:");
		for(String move : moves)
		System.out.println(move);
		System.out.println("");
		
		// prints the maze exit position
		System.out.println("goal:");
		System.out.println(maze.getGoalPosition());
		System.out.println("");
		
		try{
			// get 2d cross sections of the 3d maze
			int[][] maze2dx=maze.getCrossSectionByX(0);
			printMaze2d(maze2dx);
			
			int[][] maze2dy=maze.getCrossSectionByX(1);
			printMaze2d(maze2dy);
			
			int[][] maze2dz=maze.getCrossSectionByX(2);
			printMaze2d(maze2dz);
			
			// this should throw an exception!
			maze.getCrossSectionByX(-1);
		} 
		catch (IndexOutOfBoundsException e){
		System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		/* Simple Maze3d Generator */
		testMazeGenerator(new SimpleMaze3dGenerator());
		
		/* DFS1 Maze3d Generator */
		testMazeGenerator(new DFS1Maze3dGenerator());
		
		/* Prim Maze3d Generator */
		testMazeGenerator(new PrimMaze3dGenerator());
		
		/* DFS2 Maze3d Generator */
		testMazeGenerator(new DFS2Maze3dGenerator());

	}
	
	/* print arr [][] */
	public static void printMaze2d(int[][] arr)
	{
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("");
	}
}
