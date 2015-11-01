package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * The Class SimpleMaze3dGenerator.
 */
public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {
	
	/**
	 * Ctor
	 * Instantiates a new simple maze3d generator.
	 */
	public SimpleMaze3dGenerator() {}
	
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
		
		/* Random end */
		Position pe = new Position();
		boolean chek = true;
		while (chek) {
			pe.setPosition(maze.randomPosition());
			chek = ((ps.equals(pe)) ||  (x==0 && y==0 && z==0));		
		}
		
		maze.setGoalPosition(pe);
		maze.setValue(pe, 0);
		
		/* randomized path to end */
		ArrayList<String> direction = new ArrayList<String>() ;
		direction.add("X");
		direction.add("Y");
		direction.add("Z");
		
		Position p = new Position(ps);
		
		for (int i = 0; i < 3; i++) {
			int num = (int)(Math.random()*(direction.size()));
			if (direction.get(num).equals("X")) {
				if (ps.getX()<=pe.getX()) {
					for (int j=1 ; j<=Math.abs(ps.getX()-pe.getX()) ; j++) {
						p.setX(ps.getX()+j);
						maze.setValue(p, 0);
					}
				}
				else {
					for (int j=1 ; j<=Math.abs(ps.getX()-pe.getX()) ; j++) {
						p.setX(ps.getX()-j);
						maze.setValue(p, 0);
					}
				}
			}
			else if (direction.get(num).equals("Y")) {
					if (ps.getY()<=pe.getY()) {
						for (int j=1 ; j<=Math.abs(ps.getY()-pe.getY()) ; j++) {
							p.setY(ps.getY()+j);
							maze.setValue(p, 0);
						}
					}
					else {
						for (int j=1 ; j<=Math.abs(ps.getY()-pe.getY()) ; j++) {
							p.setY(ps.getY()-j);
							maze.setValue(p, 0);
						}
					}
			}
			else { // Z
				if (ps.getZ()<=pe.getZ()) {
					for (int j=1 ; j<=Math.abs(ps.getZ()-pe.getZ()) ; j++) {
						p.setZ(ps.getZ()+j);
						maze.setValue(p, 0);
					}
				}
				else {
					for (int j=1 ; j<=Math.abs(ps.getZ()-pe.getZ()) ; j++) {
						p.setZ(ps.getZ()-j);
						maze.setValue(p, 0);
					}
				}
			}
			direction.remove(num);
		}	
		return maze;
	}
}