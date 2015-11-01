package algorithms.search;

import java.util.ArrayList;

/**
 * The Class Astar.
 * @param <T> the generic type
 */
public class Astar<T> extends CommonSearcher<T> {

	/**  members. */
	private Heuristic<T> h;
	
	/**
	 * Instantiates a new astar.
	 * @param h the h
	 */
	// C'tor
	public Astar (Heuristic<T> h){
		this.h=h;
	}
	
	/**
	 * Sets the heuristic.
	 * @param h the new heuristic
	 */
	public void setHeuristic (Heuristic<T> h){
		this.h=h;
	}
	
	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		
		if (s==null || this.h==null) {
			return null;
		}
		
		State<T> start = s.getStartState();
		State<T> goal = s.getGoalState();
		start.setCost(h.getEvaluation(start, goal)); // price is f(x) = g_score + h_score
		openList.add(start);
		stateCost.put(start, start.getCost());
		
		while (!openList.isEmpty()) 
		{
			State<T> current = popOpenList();
			
			if (current.equals(goal)) {
				Solution<T> solution = new Solution<T> ();
				solution.setMovesSolution(generatePathToGoal(current));
				openList.clear();
				closeList.clear();
				stateCost.clear();
				return solution;
			}
			
			closeList.add(current);

			ArrayList<State<T>> nextStates = s.getAllPossibleStates(current);
			for (State<T> state : nextStates)
			{
				if (closeList.contains(state)) {
					continue;
				}
				
				double tentative = current.getCost() + state.getCost();
				boolean flag = false;
				
				if (openList.contains(state)==false) {
					flag=true;
				}
				if (flag || tentative < (stateCost.get(state.hashCode())-(h.getEvaluation(state, goal))))
				{

					state.setCameFrom(current);
					
					double f_score = tentative + h.getEvaluation(state, goal);
					state.setCost(f_score);
					
					stateCost.remove(state.hashCode());
					stateCost.put(state, f_score);
					
					if (!flag) {
						openList.remove(state);
					}
					openList.add(state);
				} 
			}
		}
		return null;
	}
}



/* Astar pseudo-code
function A*(start,goal)
    closedset := the empty set    // The set of nodes already evaluated.
    openset := {start}    // The set of tentative nodes to be evaluated, initially containing the start node
    came_from := the empty map    // The map of navigated nodes.
 
    g_score[start] := 0    // Cost from start along best known path.
    // Estimated total cost from start to goal through y.
    f_score[start] := g_score[start] + heuristic_cost_estimate(start, goal)
 
    while openset is not empty
        current := the node in openset having the lowest f_score[] value
        if current = goal
            return reconstruct_path(came_from, goal)
 
        remove current from openset
        add current to closedset
        for each neighbor in neighbor_nodes(current)
            if neighbor in closedset
                continue
            tentative_g_score := g_score[current] + dist_between(current,neighbor)
 
            if neighbor not in openset or tentative_g_score < g_score[neighbor] 
                came_from[neighbor] := current
                g_score[neighbor] := tentative_g_score
                f_score[neighbor] := g_score[neighbor] + heuristic_cost_estimate(neighbor, goal)
                if neighbor not in openset
                    add neighbor to openset
 
    return failure
 
function reconstruct_path(came_from,current)
    total_path := [current]
    while current in came_from:
        current := came_from[current]
        total_path.append(current)
    return total_path
*/
