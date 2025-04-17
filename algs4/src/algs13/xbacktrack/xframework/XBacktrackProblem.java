package algs13.xbacktrack.xframework;

/**
 * A interface defining a backtracking problem to be solved with the backtracking
 * framework.
 *
 * @param <T> The type of the choices made in the backtracking problem.
 */
public interface XBacktrackProblem<T> {
    /**
     * Initialize the problem. The initialize method must call the track method on the
     * driver to track the starting state.
     *
     * @param driver The driver orchestrating the search for the solution.
     */
    void initialize(MyBacktrackDriver<T> driver);

    /**
     * Determine if the backtracking driver can advance in searching for a solution
     * from the state represented by previous choice. If it can, this method must
     * either call the track method on the driver to track the next choice, or
     * it must call the driver's setDone() method.
     *
     * @param state The previous choice
     * @return true if the current state consists of a valid partial solution,
     *              or the current state represents a complete solution (i.e. the
     *              last choice required to solve the problem has been made).
     */
    boolean advance(T state);
}
