import java.util.Scanner;

public class GameLoop {
    private Chessboard chessboard;
    private int boardSize;
    private int moveNumber;
    private Scanner scanner;
    private int currentRow;  // currentRow をフィールドとして宣言
    private int currentCol;  // currentCol をフィールドとして宣言

    public GameLoop(int boardSize) {
        this.boardSize = boardSize;
        chessboard = new Chessboard(boardSize);
        moveNumber = 0;
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("チェス盤のスタート位置を選択してください（1-8の範囲で選んでください）");
        System.out.print("行 (1-8): ");
        currentRow = scanner.nextInt();  // currentRow を初期化
        System.out.print("列 (1-8): ");
        currentCol = scanner.nextInt();  // currentCol を初期化

        if (isValidPosition(currentRow, currentCol)) {
            currentRow--;
            currentCol--;
            chessboard.placeKnight(currentRow, currentCol);
        } else {
            System.out.println("無効な位置です。1から8の範囲で選択してください。");
            return;
        }

        // ゲームのメインループ
        while (!chessboard.isGameFinished()) {
            chessboard.printChessboard(currentRow, currentCol, moveNumber);

            System.out.println("次の移動先を選択してください（1-8の範囲で選んでください）");
            System.out.print("行 (1-8): ");
            int nextRow = scanner.nextInt();
            System.out.print("列 (1-8): ");
            int nextCol = scanner.nextInt();

            if (isValidPosition(nextRow, nextCol)) {
                nextRow--;
                nextCol--;

                // Knight オブジェクトを取得
                Knight currentKnight = chessboard.getKnight();

                if (currentKnight.isValidMove(currentRow, currentCol, nextRow, nextCol) && chessboard.isEmpty(nextRow, nextCol)) {
                    chessboard.placeKnight(nextRow, nextCol);
                    moveNumber++;
                    currentRow = nextRow;  // 現在の位置を更新
                    currentCol = nextCol;
                } else {
                    System.out.println("そのマスは無効な移動です。再度選択してください.");
                }
            } else {
                System.out.println("無効な位置です。1から8の範囲で選択してください.");
            }
        }
        System.out.println("ゲーム終了！全てのマスを巡回しました.");
        scanner.close();
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }

    public static void main(String[] args) {
        GameLoop game = new GameLoop(8);
        game.start();
    }
}