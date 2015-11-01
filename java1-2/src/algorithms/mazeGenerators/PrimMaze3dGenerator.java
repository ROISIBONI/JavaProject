package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * The Class PrimMaze3dGenerator.
 */
public class PrimMaze3dGenerator extends CommonMaze3dGenerator {

	/** Members */
	private ArrayList<Position> walls;
	
	/**
	 * Ctor
	 * Instantiates a new prim maze3d generator.
	 */
	public PrimMaze3dGenerator() {}
	
	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.CommonMaze3dGenerator#generate(int, int, int)
	 */
	@Override
	public Maze3d generate(int x, int y, int z) {
		
		maze = new Maze3d (x,y,z);
		
		maze.wallsMaze3d(1);
		
		/* Random start */
		Position ps = maze.randomPosition();
		maze.setStartPosition(ps);
		maze.setValue(ps, 0);
		
		this.walls = new ArrayList<Position>();

		addWalls(ps);
		
		int num;
		while (!walls.isEmpty()) {
			
			num = (int)(Math.random()*(walls.size()));
			
			/* Create Maze3d */
			CreateMaze3d(walls.get(num));
			
			walls.remove(num);	
		}
		
		return maze;
	}

	/**
	 * Adds the walls.
	 * @param p the p
	 */
	/* add walls near position  */
	private void addWalls (Position p)
	{
		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();
		
		Position s = new Position(p);
		
		s.setX(x+2);
		if (maze.valid(s) && maze.getValue(s)!=0) {
			s.setX(x+1);
			walls.add(new Position(s));
		}
		s.setX(x-2);
		if (maze.valid(s) && maze.getValue(s)!=0) {
			s.setX(x-1);
			walls.add(new Position(s));
		}
		s.setX(x);
		s.setY(y+2);
		if (maze.valid(s) && maze.getValue(s)!=0) {
			s.setY(y+1);
			walls.add(new Position(s));
		}
		s.setY(y-2);
		if (maze.valid(s) && maze.getValue(s)!=0) {
			s.setY(y-1);
			walls.add(new Position(s));
		}
		s.setY(y);
		s.setZ(z+2);
		if (maze.valid(s) && maze.getValue(s)!=0) {
			s.setZ(z+1);
			walls.add(new Position(s));
		}
		s.setZ(z-2);
		if (maze.valid(s) && maze.getValue(s)!=0) {
			s.setZ(z-1);
			walls.add(new Position(s));
		}
	}
	
	/**
	 * Creates the maze3d.
	 * @param p the p
	 */
	/* Create Maze3d - randomized paths */
	private void CreateMaze3d(Position p) {

		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();
		
		char typeWall;
		if (maze.getStartPosition().getX()%2 != p.getX()%2)
			typeWall='x';
		else if (maze.getStartPosition().getY()%2 != p.getY()%2)
			typeWall='y';
		else
			typeWall ='z';

		Position a = new Position(p);
		Position b = new Position(p);
		
		
		if (typeWall=='x') {
			a.setX(x+1);
			b.setX(x-1);
			if (maze.getValue(a)!=0 || maze.getValue(b)!=0) {
				if (maze.getValue(a)!=0) {
					maze.setValue(a,0);
					addWalls(a);
					maze.setGoalPosition(a);
				}
				else {
					maze.setValue(b, 0);
					addWalls(b);
					maze.setGoalPosition(b);
				}
				maze.setValue(p, 0);
			}
		}
		else if (typeWall=='y') {
			a.setY(y+1);
			b.setY(y-1);
			if (maze.getValue(a)!=0 || maze.getValue(b)!=0) {
				if (maze.getValue(a)!=0) {
					maze.setValue(a, 0);
					addWalls(a);
					maze.setGoalPosition(a);
				}
				else {
					maze.setValue(b, 0);
					addWalls(b);
					maze.setGoalPosition(b);
				}
				maze.setValue(p, 0);
			}
		}
		else { // Z
			a.setZ(z+1);
			b.setZ(z-1);
			if (maze.getValue(a)!=0 || maze.getValue(b)!=0) {
				if (maze.getValue(a)!=0) {
					maze.setValue(a, 0);
					addWalls(a);
					maze.setGoalPosition(a);
				}
				else {
					maze.setValue(b, 0);
					addWalls(b);
					maze.setGoalPosition(b);
				}
				maze.setValue(p, 0);
			}
		}
	}
}