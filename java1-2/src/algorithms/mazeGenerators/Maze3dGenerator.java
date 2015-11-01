package algorithms.mazeGenerators;

/**
 * The Interface Maze3dGenerator.
 */
public interface Maze3dGenerator {

	/**
	 * Generate.
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the maze3d
	 */
	Maze3d generate (int x, int y, int z);
	
	/**
	 * Measure algorithm time.
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the string
	 */
	String measureAlgorithmTime (int x, int y, int z);
	
}
