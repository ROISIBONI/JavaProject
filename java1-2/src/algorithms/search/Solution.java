package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Solution.
 * @param <T> the generic type
 */
@SuppressWarnings("serial")
public class Solution<T> implements Serializable {

	/**  members */
	private ArrayList<T> movesSolution;

	/**
	 * Ctor
	 * Instantiates a new solution.
	 */
	public Solution (){
		movesSolution=null;
	}
	
	/**
	 * copy Ctor
	 * Instantiates a new solution.
	 * @param copySolution the copy solution
	 */
	public Solution (Solution<T> copySolution){
		movesSolution=copySolution.getMovesSolution();
	}

	/**
	 * Gets the moves solution.
	 * @return the moves solution
	 */
	public ArrayList<T> getMovesSolution() {
		return movesSolution;
	}

	/**
	 * Sets the moves solution.
	 * @param movesSolution the new moves solution
	 */
	public void setMovesSolution(ArrayList<T> movesSolution) {
		this.movesSolution = movesSolution;
	}
	
	/**
	 * Prints the.
	 */
	public void print() {
		for (int i = 0; i < movesSolution.size(); i++) {
			System.out.println(movesSolution.get(i).toString()); 
		}
	}
	
	public String[] toArrString() {
		String [] arrString = new String [movesSolution.size()];
		for (int i = 0; i < movesSolution.size(); i++) {
			arrString[i]=movesSolution.get(i).toString();
		}
		return arrString;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solution<?> other = (Solution<?>) obj;
		if (movesSolution == null) {
			if (other.movesSolution != null)
				return false;
		} else if (!movesSolution.equals(other.movesSolution))
			return false;
		return true;
	}
	

}
