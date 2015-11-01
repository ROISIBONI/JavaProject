package algorithms.search;

import algorithms.mazeGenerators.Position;
import algorithms.search.State;

/**
 * The Class MazeAirDistance.
 */
public class MazeAirDistance implements Heuristic<Position> {

	/* (non-Javadoc)
	 * @see algorithms.search.Heuristic#getEvaluation(algorithms.search.State, algorithms.search.State)
	 */
	@Override
	public int getEvaluation(State<Position> state, State<Position> goal) {
		int x = (int) Math.pow(state.getState().getX() - goal.getState().getX(),2);
		int y = (int) Math.pow (state.getState().getY() - goal.getState().getY(),2);
		int z = (int) Math.pow (state.getState().getZ() - goal.getState().getZ(),2);
		return ((int) Math.sqrt(x+y+z));
	}

}
