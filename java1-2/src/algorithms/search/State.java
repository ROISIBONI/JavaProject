package algorithms.search;

/**
 * The Class State.
 * @param <T> the generic type
 */
public class State <T> implements Comparable<State<T>> {
	
	/** Members */
	private T state;
	private double cost;
    private State<T> cameFrom;

	/**
	 * Ctor
	 * Instantiates a new state.
	 * @param state the state
	 */
    public State(T state){       
        this.state = state;
    }

    /**
     * Gets the state.
     * @return the state
     */
	public T getState() {
		return state;
	}
	
	/**
	 * Gets the cost.
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * Gets the came from.
	 * @return the came from
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}

	/**
	 * Sets the state.
	 * @param state the new state
	 */
	public void setState(T state) {
		this.state = state;
	}

	/**
	 * Sets the cost.
	 * @param cost the new cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	/**
	 * Sets the came from.
	 * @param cameFrom the new came from
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
/*	@Override
    public boolean equals(Object obj){
		if (obj==null) {
			return false;
		}
		//if ((State<T>)obj == this) {
		//	return true;
		//}
        return state.equals(((State<T>)obj).getState());
    } */
	
	
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return state.toString().hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(State<T> o) {
		if (this.getCost() > o.getCost())
			return 1;
		else if (this.getCost() < o.getCost())
			return -1;
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		State other = (State) obj;
		return state.equals(other.getState());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return state.toString();
	}
}
