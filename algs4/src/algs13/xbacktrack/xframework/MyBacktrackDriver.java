package algs13.xbacktrack.xframework;

import algs13.Stack;
import stdlib.StdOut;

/**
 * The driver for the backtracking framework. In order to run the driver,
 * this class must be instantiated and the solve method on the instance of this
 * class must be called.
 *
 * Find the TODOs in this file and implement them. There are also TODOs in
 * xbacktrack.xsudoku.MySudoku (MySudoku.java) for you to complete.
 *
 * This framework is adapted from Timothy Budd: Classic Data Structures in C++,
 * Addison-Wesley, 1994. The design is modernized to use object composition
 * rather than inheritance. It can be used to model any kind of backtracking
 * problem.
 *
 * @param <T> The type of the choices made in the backtracking problem.
 */
public final class MyBacktrackDriver<T> {

    // A reference to the problem instance
    private final XBacktrackProblem<T> problem;

    // The stack used to track the choices we attempt in solving
    // the problem. If the framework solves the problem, the
    // stack will contain the choices that were made in the search
    // for a solution.
    private final Stack<T> theStack = new Stack<>();

    // A counter to track how many times we backtrack.
    private long backtrackCount;

    // A boolean indicating whether the backtracking problem is solved.
    // The setDone() method will set this flag to true.
    private boolean done = false;

    // Constructor for the driver, which orchestrates finding a solution
    // for the problem given.
    public MyBacktrackDriver(XBacktrackProblem<T> problem) {
        if(problem == null) throw new IllegalArgumentException("Backtracking problem must not be null");
        backtrackCount = 0;
        this.problem = problem;
    }

    // Set the done flag to true. This signals to the run method that it stop
    // the backtracking loop.
    public void setDone() {
        this.done = true;
    }

    // Track a choice made representing a search area.
    public void track(T choice) {
        if(choice == null) throw new IllegalArgumentException("Cannot track a null choice");

        // TODO: Track the choice in the stack.
    }

    // Backtrack - throw away the most recent choice and back up to a previous choice to
    // see if a different choice can be made in order to make progress.
    private void backtrack() {
        backtrackCount++;

        // TODO: Backtrack: throw away the most recent choice and go back to the immediate
        // previous choice we made to see if we can make a different choice.
    }

    // TODO: Implement this method.
    private void run() {
        // Run the main backtracking loop. You will need to create a loop.
        
        // Until the done flag is flipped to true:

        // If there is no way we can build on the most recent choice we made (i.e. the
        // element that is at the top of the stack), stop and return.

        // If there is a choice at the top of the stack, see if we can make progress after
        // having made that choice. As we are checking, we need to look at it, not remove
        // it from the stack, and pass it to the advance method on the backtracking problem.

        // If we can advance, keep going, unless the problem implementation
        // flipped the done flag to true. If we cannot advance, we must call driver.backtrack();
    }

    /**
     * A method to solve the problem.
     * @return a result representing the solution
     */
    public XBacktrackResult solve() {
        problem.initialize(this);
        run();

        StdOut.format("Backtracked %,d times\n", backtrackCount);
        if(!done || theStack.isEmpty()) return new XBacktrackFailure();

        // Reverse the order of the choices from the stack.
        Stack<T> s = new Stack<>();
        while(!theStack.isEmpty()) {
            s.push(theStack.pop());
        }

        return new XBacktrackSuccess<>(s);
    }
}
