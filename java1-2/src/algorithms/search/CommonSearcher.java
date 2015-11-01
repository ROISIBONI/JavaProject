package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * The Class CommonSearcher.
 * @param <T> the generic type
 */
public abstract class CommonSearcher<T> implements Searcher<T> {

	/** Members */
	protected PriorityQueue<State<T>> openList;
	protected HashSet<State<T>> closeList;
	protected HashMap<State<T>,Double> stateCost;
	
	/** The evaluated nodes. */
	private int evaluatedNodes;
	
	 /**
 	 * Instantiates a new common searcher.
 	 */
 	public CommonSearcher() {
		openList=new PriorityQueue<State<T>>();
		closeList = new HashSet<State<T>> ();
		stateCost = new HashMap<State<T>,Double>();
		evaluatedNodes=0;
	}
	
	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#getNumberOfNodesEvaluated()
	 */
	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}

	/**
	 * Sets the evaluated nodes.
	 * @param evaluatedNodes the new evaluated nodes
	 */
	public void setEvaluatedNodes(int evaluatedNodes) {
		this.evaluatedNodes = evaluatedNodes;
	}

	/**
	 * Pop open list.
	 * @return the state
	 */
	protected State<T> popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	 }

	/**
	 * Generate path to goal.
	 * @param state the state
	 * @return the array list
	 */
	// method return Solving actions - run goal  back until start
	protected ArrayList<T> generatePathToGoal(State<T> state) {
		ArrayList<T> movesSolution = new ArrayList<T>();
		movesSolution.add(0, state.getState());
		while (state.getCameFrom() != null) {
			movesSolution.add(0, state.getCameFrom().getState());
			state = state.getCameFrom();	
		}
		return movesSolution;
	}
}
