package algorithms.search;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class BFS.
 * @param <T> the generic type
 */
public class BFS<T> extends CommonSearcher<T> {
	
	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		
		this.openList.add(s.getStartState());
		this.stateCost.put(s.getStartState(), s.getStartState().getCost());
		
		while (!openList.isEmpty())
		{
			State<T> vertex = popOpenList();
			closeList.add(vertex);
			
			if (vertex.equals(s.getGoalState())) {	
				Solution<T> solution = new Solution<T> ();
				solution.setMovesSolution(generatePathToGoal(vertex));
				openList.clear();
				closeList.clear();
				stateCost.clear();
				return solution;
			}
			
			ArrayList<State<T>> nextStates = s.getAllPossibleStates(vertex);
			
			for (State<T> state : nextStates) {
				
				if (openList.contains(state)==false && closeList.contains(state)==false) {
					state.setCameFrom(vertex);
					state.setCost(vertex.getCost()+state.getCost());
					openList.add(state);
					stateCost.remove(state.hashCode());
					stateCost.put(state, state.getCost());
				}
				else if (stateCost.get(state) < state.getCost()) {
					openList.remove(state);
					state.setCameFrom(vertex);
					state.setCost(stateCost.get(state));
					openList.add(state);
				}
			}
		}
		return null;
	}
}

/* Best-First-Search pseudo-code
	BFS(Start,Goal)
OPEN = [initial state]	// a priority queue of states to be evaluated
CLOSED = []	// a set of states already evaluated
while OPEN is not empty
do
   1. n < dequeue(OPEN) // Remove the best node from OPEN
   2. add(n,CLOSED)	      // so we won’t check n again
   3. If n is the goal state, 
          backtrace path to n (through recorded parents) and return path.
   4. Create n's successors.
   5. For each successor s do:
   	a. If s is not in CLOSED and s is not in OPEN: 
		i.  update that we came to s from n
		ii. add(s,OPEN) 
	b. Otherwise, if this new path is better than previous one
        i.  If it is not in OPEN add it to OPEN. 
		ii. Otherwise, adjust its priority in OPEN
done
*/
