public class Chessboard {
    private int size;
    private int[][] board;
    private Knight knight;

    public Chessboard(int size) {
        this.size = size;
        board = new int[size][size];
        knight = new Knight(this);
    }
    public Knight getKnight() {
        return knight;
    }
    public void placeKnight(int row, int col) {
        board[row][col] = 1;
    }

    public boolean isEmpty(int row, int col) {
        return board[row][col] == 0;
    }

    public boolean isGameFinished() {//参照をしながら処理を軽く済ます
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printChessboard(int currentRow,int currentCol,int moveNumber) {
        System.out.print("  ");
        for (int col = 0; col < size; col++) {
            System.out.print(col + 1 + " ");
        }
        System.out.println();

        for (int row = 0; row < size; row++) {
            System.out.print(row + 1 + " ");
            for (int col = 0; col < size; col++) {
                if ( board[row][col] == 1) {
                    System.out.print("K ");
                    board[row][col] ++;
                } else if (board[row][col] == 0) {
                    if (isValidMove(currentRow, currentCol, row,col) ){
                        System.out.print("o ");
                    } else {
                        System.out.print("× ");
                    }
                } else {
                    System.out.print("済 ");
                }
            }
            System.out.println();
        }
        System.out.println("手数: " + moveNumber);
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
}