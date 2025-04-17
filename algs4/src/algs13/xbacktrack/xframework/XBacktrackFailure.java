package algs13.xbacktrack.xframework;

/**
 * An unsuccessful solution to a backtracking problem.
 */
public final class XBacktrackFailure implements XBacktrackResult {
    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public String toString() {
        return "No solution!";
    }
}
