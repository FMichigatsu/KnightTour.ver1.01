public class Knight {
    private Chessboard chessboard;
    private int row;
    private int col;

    public Knight(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isValidMove(int currentRow, int currentCol, int nextRow, int nextCol) {
        int[] rowMoves = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] colMoves = {1, 2, 2, 1, -1, -2, -2, -1};
        for (int i = 0; i < 8; i++) {
            int possibleRow = currentRow + rowMoves[i];
            int possibleCol = currentCol + colMoves[i];
            if (possibleRow == nextRow && possibleCol == nextCol) {
                return true;
            }
        }
        return false;
    }

    public void moveTo(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
