package algorithms.search;

import algorithms.search.State;

/**
 * The Interface Heuristic.
 *
 * @param <T> the generic type
 */
public interface Heuristic<T> {

	/**
	 * Gets the evaluation.
	 * @param state the state
	 * @param goal the goal
	 * @return the evaluation
	 */
	public int getEvaluation(State<T> state, State<T> goal);
	
}
