package algs13.xbacktrack.xframework;

import algs13.Stack;

import java.util.Iterator;

/**
 * A successful solution to a backtracking problem. The choices
 * are provided in a stack, in reverse order from which they were
 * determined.
 *
 * @param <T> The type of the choices made in the backtracking problem.
 */
public final class XBacktrackSuccess<T> implements XBacktrackResult {
    private final Stack<T> choices;
    public XBacktrackSuccess(Stack<T> choices) {
        this.choices = choices;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Solution: [ ");
        Iterator<T> it = choices.iterator();
        while(it.hasNext()) {
            builder.append(it.next());
            if(it.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append(" ]");
        return builder.toString();
    }
}
