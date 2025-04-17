package algs13.xbacktrack.xsudoku;

/**
 * A cell in a Sudoku grid.
 *
 * Each cell has an x (row) coordinate and y (column) coordinate, and is part of
 * a subgrid. These properties are fixed at the time a cell is constructed.
 */
abstract class XSudokuCell {
    final int x;
    final int y;
    final int subgridId;

    XSudokuCell(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.subgridId = (x / MySudoku.SUBGRID_DIMENSION) + MySudoku.SUBGRID_DIMENSION * (y / MySudoku.SUBGRID_DIMENSION);
    }

    /**
     * Get the digit currently associated with this cell.
     * @return the digit associated with this cell
     */
    abstract int getDigit();
}
