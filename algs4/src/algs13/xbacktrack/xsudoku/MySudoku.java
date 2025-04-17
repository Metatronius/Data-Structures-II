package algs13.xbacktrack.xsudoku;

import algs13.xbacktrack.xframework.MyBacktrackDriver;
import algs13.xbacktrack.xframework.XBacktrackProblem;
import algs13.xbacktrack.xframework.XBacktrackResult;
import stdlib.StdOut;
import stdlib.Stopwatch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * A Sudoker solver, implemented as a BacktrackProblem where each
 * choice is represented by a MutableCell (a cell we can assign a
 * digit as part of a possible solution to Sudoku).
 *
 * This project represents a more substantial Java program than
 * what we have been working with so far in the course. Search in this
 * file and in algs13.xbacktrack.xframework.MyBacktrackDriver
 * (MyBacktrackDriver.java)for some TODOs that will direct you to what you
 * need to do for this assignment.
 */
public final class MySudoku implements XBacktrackProblem<XMutableCell> {

    public static final int SUBGRID_DIMENSION = 3;
    public static final int GRID_SIZE = SUBGRID_DIMENSION * SUBGRID_DIMENSION;
    private final XSudokuCell[][] grid;

    // This is a mapping of subgrid id to the set of all cells in the subgrid.
    // When the solver tries assigning a digit to a cell, that digit must
    // not exist in another cell in the subgrid. This provides a quick way
    // to find all the cells in a given subgrid so this property can be checked.
    private final HashMap<Integer, Set<XSudokuCell>> subgrids;

    final XMutableCell firstCell;
    final XMutableCell lastCell;

    private MyBacktrackDriver<XMutableCell> driver;

    /**
     * The constructor takes GRID_SIZE array of strings, each of which must be GRID_SIZE in
     * length. Each character must be a digit. The index of the string corresponds to the row in the
     * grid and the index of the digit in the string corresponds to the column in the grid.
     *
     * If a digit is 0, that signifies that the cell in the grid is mutable and the digit in that
     * cell will be assigned by the solver.
     */
    MySudoku(final String[] input) {
        if(input.length != GRID_SIZE) {
            throw new IllegalArgumentException(String.format("Input must have %d rows, found %d rows", GRID_SIZE, input.length));
        }

        grid = new XSudokuCell[GRID_SIZE][];
        subgrids = new HashMap<>();
        for(int i = 0; i < GRID_SIZE; i++) {
            subgrids.put(i, new HashSet<>());
        }

        XMutableCell next = null;
        XMutableCell last = null;
        for(int x = GRID_SIZE - 1; x >= 0; x--) {
            if(input[x].length() != GRID_SIZE) {
                throw new IllegalArgumentException(
                    String.format("Input must have %d columns, found %d columns", GRID_SIZE, input[x].length())
                );
            }
            grid[x] = new XSudokuCell[GRID_SIZE];
            for(int y = GRID_SIZE - 1; y >= 0; y--) {
                char c = input[x].charAt(y);
                final int digit = c - 48;
                if(digit < 0 || digit > GRID_SIZE) {
                    throw new IllegalArgumentException(String.format("Invalid digit %d at location (%d, %d)", digit, x, y));
                }
                if(digit == 0) {
                    XMutableCell cell = new XMutableCell(x, y, next);
                    grid[x][y] = cell;
                    next = cell;
                    if(last == null) last = next;
                }
                else {
                    grid[x][y] = new XImmutableCell(x, y, digit);
                }
                Set<XSudokuCell> subgrid = subgrids.get(grid[x][y].subgridId);
                subgrid.add(grid[x][y]);
            }
        }
        // Start out at a dummy cell - the first choice is what to do with the first
        // cell in the grid, but if we backtrack from that choice, the framework
        // we need this to be in the stack to prevent stack underflow.
        firstCell = new XMutableCell(-1, -1, next);
        if(last == null) {
            throw new IllegalStateException("No last cell!");
        }
        this.lastCell = last;
    }

    @Override
    public void initialize(final MyBacktrackDriver<XMutableCell> driver) {
        this.driver = driver;
        driver.track(firstCell); // ... which happens to be the dummy cell!
    }

    private XMutableCell findNextMove(final XMutableCell current) {
        if(current != null) {
            int nextDigit = current.nextDigit();
            // When nextDigit wraps around to 0, we have tried all digits that
            // are valid for the current cell.
            while(nextDigit != 0) {
                if(isValid(current)) {
                    return current;
                }
                nextDigit = current.nextDigit();
            }
        }
        // There is no next move we can make in the current cell.
        return null;
    }

    // Verify the puzzle solution is correct.
    private boolean verify() {
        for(int x = 0; x < GRID_SIZE; x++)
            for(int y = 0; y < GRID_SIZE; y++)
                if(!isValid(grid[x][y])) return false;
        return true;
    }

    // Method to determine whether the cell, with the digit updated
    // prior to this call, is valid given the current state of the puzzle.
    private boolean isValid(final XSudokuCell cell) {
        final int subgrid = cell.subgridId;
        final int digit = cell.getDigit();
        final int row = cell.x;
        final int col = cell.y;
        // Count how many times the digit for this cell occurs in its
        // row, column, and subgrid. If the row, column, or subgrid has
        // more than one occurrence of this digit, the cell is not valid.
        int rowCount = 0;
        int colCount = 0;
        int subgridCount = 0;
        for(int i = 0; i < GRID_SIZE; i++) {
            if(grid[row][i].getDigit() == digit) rowCount++;
            if(grid[i][col].getDigit() == digit) colCount++;
        }
        for(final XSudokuCell c : subgrids.get(subgrid)) {
            if(c.getDigit() == digit) subgridCount++;
        }
        return rowCount <= 1 && colCount <= 1 && subgridCount <= 1;
    }

    // Given the previous choice of a digit on a mutable cell,
    // determine whether we can advance from this choice.
    @Override
    public boolean advance(final XMutableCell previous) {
        if(previous == lastCell) {
            // The puzzle is solved!
            driver.setDone();
            return true;
        }
        // Find the next valid move for the next cell for which we need to make a choice.
        // If there is none, we can't proceed with the previous choice we made.
        final XMutableCell nextMove = findNextMove(previous.nextCell);
        if(nextMove == null) {
            return false;
        }

        // We found a move we can try.
        // Track it.
        driver.track(nextMove);
        
        // The the framework to keep building on the choice we just tracked.
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                builder.append(grid[i][j].getDigit());
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    // TODO: Start here. Read over the code and comments here first. Don't worry about all the
    // code you see in this assignment. Focus on the TODOs I have provided. You may wish to
    // explore the code in detail at some point, but you don't need to understand all of it
    // in order to succeed on this assignment.
    // 
    // This is the main program you will run to test out your solution.
    public static void main(final String[] args) {

        // A Sudoku grid consists of 9 rows and 9 columns, and has 9 3x3 subgrids. Each
        // cell is in exactly one subgrid. The subgrids, assigned letters A-I here for
        // illustration, are as follows:
        //
        //   A A A | B B B | C C C
        //   A A A | B B B | C C C
        //   A A A | B B B | C C C
        //   ------+-------+------
        //   D D D | E E E | F F F
        //   D D D | E E E | F F F
        //   D D D | E E E | F F F
        //   ------+-------+------
        //   G G G | H H H | I I I
        //   G G G | H H H | I I I
        //   G G G | H H H | I I I
        //
        // Each location in the grid is a cell, which may have an immutable digit as part
        // of the puzzle definition, or a mutable digit which the Sudoku solver will assign.
        // Sudoku puzzles of some number of pre-filled cells that serve as clues.
        //
        // No digit may appear more than once in any row, column, or subgrid.
        //
        // The Sudoku puzzle below has 17 clues.
        //
        // If we were to use brute force to solve a 9x9 Sudoku puzzle with 17 clues,
        // we would need to check all possible combinations of digits for the empty cells
        // until we find a combination that satisfies all the constraints of Sudoku.
        // Since there are 64 empty cells in a 9x9 Sudoku puzzle with 17 clues, each of
        // which can be filled with 9 possible digits, the total number of possible
        // combinations is 9^64.
        //
        // This is an astronomically large number, and it would take an incredibly long
        // time to check all possible combinations, even with the most powerful computers
        // available today. In fact, it's estimated that it would take billions of years
        // to solve a single 9x9 Sudoku puzzle using brute force.
        //
        // Fortunately, there are much more efficient algorithms and strategies that can
        // be used to solve Sudoku puzzles without having to resort to brute force. One
        // such technique is called backtracking. With backtracking, we repeatedly try
        // choices we can make to solve a problem, continually building on previous choices.
        // When we determine that a particular choice will not work, we backtrack to an earlier
        // decision point and try out a different choice. When we employ this strategy,
        // we don't have to check every possible solution to a problem independently. When we
        // backtrack, we eliminate a very, very large number of possible solutions and reduce
        // the search space significantly.
        //
        // On my machine, the backtracking framework solves the puzzle below in about 1 minute.
        // If you do everything correctly, you should see a message stating that the program
        // backtracked 58,192,225 times.
        //
        // In this implementation of a Sudoku solver, we start at the upper left cell in the
        // grid, working left to right, trying out possible digits from 1 to 9. When we cannot
        // place a digit in a cell based on information we already have, we do not bother to
        // try it. When we can place a digit in a cell, we need to investigate whether that choice
        // can be fruitful in determining a solution. If we determine that the previous choice
        // we made cannot be fruitful, all possible solutions with that digit in that cell are
        // abandoned, and the next digit in that cell is tried. This process continues until all
        // empty cells are filled with valid digits, and we arrive at a solution to the puzzle.
        // If there is no solution, the puzzle is defective: the grid with the clues originally
        // provided would not form a valid puzzle.
        //
        // See https://en.wikipedia.org/wiki/Backtracking for more information about backtracking.
        // TODO: Scroll down to Examples in the Wikipedia article to see an animation of a Sudoku
        // puzzle being solved using backtracking. That is essentially what this program is doing.
        //
        // TODO: Your task: Find the TODOs in algs13.xframework.MyBacktrackDriver.
        // You will implement the methods that power the xframework that solves backtracking
        // problems. You will know you have succeeded if the program prints a valid solution to the
        // problem. This exercise should serve as a powerful example of what you can do with a stack,
        // and illustrate why we study data structures, and in particular stacks.
        //
        // TODO: Additional question for you: Find the linked list in this class. Note that we are
        // NOT using a class called Node, although there is something analogous to it. Your answer
        // should indicate the name of the class that is analogous to the Node, and the name of the
        // variable holding the first pointer. NOTE that the linked list I am looking for is part of
        // this implementation of the specific problem (and not in the MyBacktrackDriver) and does not
        // involve the stack used by the driver although the stack does happen to use a linked list.
        // HINT: Look in the advance method for a "pointer" (pun intended!). If you look at the
        // constructor for this class, named MySudoku, you will see the list being constructed in
        // reverse, from the lower right-hand side of the grid to the upper left-hand side.
        // 
        // TODO: The linked list "Node" class name is:
        //
        // TODO: The linked list first pointer is the variable in this class named:
        //
        String[] input = {
            "000060100",
            "300070000",
            "000000000",
            "000300620",
            "400000500",
            "700000000",
            "000207003",
            "006000080",
            "010400000"
        };

        final MySudoku puzzle = new MySudoku(input);

        final MyBacktrackDriver<XMutableCell> driver = new MyBacktrackDriver<>(puzzle);
        final Stopwatch s = new Stopwatch();
        final XBacktrackResult result = driver.solve();
        final double time = s.elapsedTime();

        StdOut.println();
        if(result.isSuccess()) {
            StdOut.format("Got successful result: in %f seconds\n%s", time, puzzle);
            StdOut.format("Solution is verified? %s", puzzle.verify());
        }
        else {
            StdOut.println(result);
        }
    }
}
