package algorithms.search;

/**
 * The Interface Searcher.
 * @param <T> the generic type
 */
public interface Searcher<T> {

	/**
	 * Search.
	 * @return the solution
	 */
    public Solution<T> search(Searchable<T> s);
    
    /**
     * Gets the number of nodes evaluated.
     * @return the number of nodes evaluated
     */
    public int getNumberOfNodesEvaluated();
}
