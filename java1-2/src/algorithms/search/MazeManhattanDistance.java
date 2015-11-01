package algorithms.search;

import algorithms.mazeGenerators.Position;
import algorithms.search.State;

/**
 * The Class MazeManhattanDistance.
 */
public class MazeManhattanDistance implements Heuristic<Position> {

	/* (non-Javadoc)
	 * @see algorithms.search.Heuristic#getEvaluation(algorithms.search.State, algorithms.search.State)
	 */
	@Override
	public int getEvaluation(State<Position> state, State<Position> goal) {
		int x = Math.abs (state.getState().getX() - goal.getState().getX());
		int y = Math.abs (state.getState().getY() - goal.getState().getY());
		int z = Math.abs (state.getState().getZ() - goal.getState().getZ());
		return (x+y+z);
	}
}
