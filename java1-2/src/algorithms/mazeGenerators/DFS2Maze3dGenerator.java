package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * The Class DFS2Maze3dGenerator.
 */
public class DFS2Maze3dGenerator extends CommonMaze3dGenerator {
	
	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.CommonMaze3dGenerator#generate(int, int, int)
	 */
	/* Creating a Maze3d */
	@Override
	public Maze3d generate(int x, int y, int z) {
	 
		maze = new Maze3d (x,y,z);
		
		maze.wallsMaze3d(1);
		
		/* Random start */
		Position ps = maze.randomPosition();
		maze.setStartPosition(ps);
		maze.setValue(ps, 0);
		
		CreateMaze3d(ps);
		
		return maze;
	}
	
	/**
	 * Creates the maze3d.
	 * @param s the s
	 */
	/* Create Maze3d  - randomized paths */
	private void CreateMaze3d(Position s) {
		
		int num;
		int x = s.getX();
		int y = s.getY();
		int z = s.getZ();
				
		ArrayList<String> direction = new ArrayList<String>() ;
		direction.add("up");
		direction.add("down");
		direction.add("right");
		direction.add("left");
		direction.add("forward");
		direction.add("back");
	
		Position p = new Position(s);
		
		for (int i = 0; i<6; i++)
		{
			num = (int)(Math.random()*(direction.size()));
			
			if (direction.get(num).equals("up")){
				p.setY(y+1);
				if (maze.valid(p) && maze.getValue(p)!=0 && maze.numAroundPosition(p,0)==1) {
						maze.setValue(p,0);
						maze.setGoalPosition(p);
						CreateMaze3d(p);		
				} 
			}
			else if (direction.get(num).equals("down")){
				p.setY(y-1);
				if (maze.valid(p) && maze.getValue(p)!=0 && maze.numAroundPosition(p,0)==1) {
						maze.setValue(p,0);
						maze.setGoalPosition(p);
						CreateMaze3d(p);		
				} 
			}
			else if (direction.get(num).equals("right")){
				p.setX(x+1);
				if (maze.valid(p) && maze.getValue(p)!=0 && maze.numAroundPosition(p,0)==1) {
						maze.setValue(p,0);
						maze.setGoalPosition(p);
						CreateMaze3d(p);		
				} 
			}
			else if (direction.get(num).equals("left")){
				p.setX(x-1);
				if (maze.valid(p) && maze.getValue(p)!=0 && maze.numAroundPosition(p,0)==1) {
						maze.setValue(p,0);
						maze.setGoalPosition(p);
						CreateMaze3d(p);		
				} 
			}
			else if (direction.get(num).equals("forward")){
				p.setZ(z+1);
				if (maze.valid(p) && maze.getValue(p)!=0 && maze.numAroundPosition(p,0)==1) {
						maze.setValue(p,0);
						maze.setGoalPosition(p);
						CreateMaze3d(p);		
				} 
			}
			else if (direction.get(num).equals("back")){
				p.setZ(z-1);
				if (maze.valid(p) && maze.getValue(p)!=0 && maze.numAroundPosition(p,0)==1) {
						maze.setValue(p,0);
						maze.setGoalPosition(p);
						CreateMaze3d(p);		
				} 
			}
			p.setPosition(s);
			direction.remove(num);
		}
	}
}
