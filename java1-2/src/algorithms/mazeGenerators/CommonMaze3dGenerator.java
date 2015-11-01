package algorithms.mazeGenerators;

/**
 * The Class CommonMaze3dGenerator.
 */
public abstract class CommonMaze3dGenerator implements Maze3dGenerator {

	/**  Members. */
	protected Maze3d maze;
	
	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Maze3dGenerator#generate(int, int, int)
	 */
	/* Abstract method - Creating a Maze3d */
	public abstract Maze3d generate (int x, int y, int z);
	
	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Maze3dGenerator#measureAlgorithmTime(int, int, int)
	 */
	/* Time creating a Maze3d */
	@Override
	public String measureAlgorithmTime(int x, int y, int z) {
		long timeS = System.currentTimeMillis();
		generate(x, y, z);
		long timeE = System.currentTimeMillis();
		return ((timeE-timeS)+"");
	}
}
