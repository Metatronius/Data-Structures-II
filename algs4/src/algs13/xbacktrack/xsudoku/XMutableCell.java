package algs13.xbacktrack.xsudoku;

/**
 * A mutable cell in a Sudoku grid. A mutable cell's digit can be
 * changed as the solver works to find the solution to the puzzle.
 */
final class XMutableCell extends XSudokuCell {

    // The next cell for which a choice will be made after
    // a choice is made for this cell. If the next cell is null,
    // this cell is the last cell for which we need to choose a digit.
    final XMutableCell nextCell;

    // The current digit assigned to this mutable cell.
    // A digit value of 0 indicates that the digit for the cell
    // has not been assigned.
    private int digit;

    // Constructor for a Mutable cell.
    XMutableCell(final int x, final int y, final XMutableCell next) {
        super(x, y);
        this.nextCell = next;
        digit = 0;
    }

    // Get the current digit assigned to this cell.
    int getDigit() {
        return digit;
    }

    // Get the next digit to try for this cell.
    // If the digit value wraps around to 0, we know we've tried all valid
    // digits for this cell that could be tried given all the previous choices
    // we have made. In this case we cannot try anything else, so the digit we
    // assigned to the previously chosen cell cannot yield a valid result for
    // the Sudoku puzzle.
    int nextDigit() {
        digit = (digit + 1) % (MySudoku.GRID_SIZE + 1);
        return digit;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d): %d", x, y, digit);
    }
}
