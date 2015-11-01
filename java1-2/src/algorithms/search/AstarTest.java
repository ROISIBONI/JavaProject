package algorithms.search;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algorithms.demo.Maze3dSearchble;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class AstarTest {

	
	private Maze3d m;
	private Solution<Position> solution;
	
	
	@Before
	public void setUp() throws Exception {
		
		int[][][] maze={
						{
							{0,0,0,0,1},
							{1,1,1,0,1},
							{0,0,0,0,0},
							{0,1,1,0,1},
							},
							
							{
							{0,1,1,1,1},
							{1,1,1,1,1},
							{0,1,1,1,1},
							{1,1,1,0,1},
							},
							
							{
							{1,1,1,1,1},
							{0,1,1,1,1},
							{0,1,1,1,0},
							{1,1,1,0,0},
							}
					
						};
		
		Position start = new Position(0,0,0);
		Position goal = new Position(2,2,4);
		
		this.m = new Maze3d(start, goal, maze);
		
		ArrayList<Position> s = new ArrayList <Position>();
		s.add(new Position(0,0,0));
		s.add(new Position(0,0,1));
		s.add(new Position(0,0,2));
		s.add(new Position(0,0,3));
		s.add(new Position(0,1,3));
		s.add(new Position(0,2,3));
		s.add(new Position(0,3,3));
		s.add(new Position(1,3,3));
		s.add(new Position(2,3,3));
		s.add(new Position(2,3,4));
		s.add(new Position(2,2,4));
		
		this.solution = new Solution<Position>();
		this.solution.setMovesSolution(s);		
		
	}

	@Test
	public void testSearch() {
		
		Searchable<Position> mazeGame = new Maze3dSearchble (m);
		
		Searcher<Position> Astar_AirDistance = new Astar<Position>(new MazeAirDistance());
		Searcher<Position> Astar_ManhattanDistance = new Astar<Position>(new MazeManhattanDistance());
		
		Solution<Position> a = Astar_AirDistance.search(mazeGame);
		Solution<Position> b = Astar_ManhattanDistance.search(mazeGame);

		Assert.assertEquals(a,solution);
		Assert.assertEquals(b,solution);
		
		fail("Not yet implemented");
	}

}
