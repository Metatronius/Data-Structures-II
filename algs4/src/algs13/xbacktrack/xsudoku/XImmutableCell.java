package algs13.xbacktrack.xsudoku;

/**
 * An immutable cell represents a clue provided in the Sudoku puzzle.
 * The digit associated with an immutable cell cannot be changed
 * by the solver.
 */
final class XImmutableCell extends XSudokuCell {

    private final int digit;

    XImmutableCell(final int x, final int y, int digit) {
        super(x, y);
        this.digit = digit;
    }

    @Override
    int getDigit() {
        return digit;
    }
}
