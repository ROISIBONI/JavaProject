package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

/**
 * The Class Maze3dSearchble.
 */
public class Maze3dSearchble implements Searchable<Position> {

	/** members. */
	private Maze3d maze;
	
	/**
	 * Ctor
	 * Instantiates a new maze3d searchble.
	 * @param maze the maze
	 */
	public Maze3dSearchble(Maze3d maze){
		this.maze=maze;
	}
	
	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getStartState()
	 */
	@Override
	public State<Position> getStartState() {
		return (new State<Position>(maze.getStartPosition()));
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getGoalState()
	 */
	@Override
	public State<Position> getGoalState() {
		return (new State<Position>(maze.getGoalPosition()));
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getAllPossibleStates(algorithms.search.State)
	 */
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		ArrayList<Position> arr = maze.getAllPossibleMoves(s.getState());
		ArrayList<State<Position>> allPossibleStates = new ArrayList<State<Position>> (arr.size());
		
		State<Position> state = null;
		for (int i = 0; i < arr.size(); i++) {
			state=new State<Position> (arr.get(i));
			state.setCost(1);
			allPossibleStates.add(state);
		}
		return allPossibleStates;
	}
}
