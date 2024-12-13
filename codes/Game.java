import java.util.Scanner;
import java.lang.Thread;

public class Game {
    int boardSize;
    char[][] board;

    Game(int boardSize) {
        this.boardSize = boardSize;
        this.board = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '□';
            }
            System.out.println();
        }
    }

    void print_state() {
        System.out.print("Everything on the console will cleared");
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        // 사용자에게 board크기값을 받아 board를 생성
        Scanner sc = new Scanner(System.in);
        int boardSize = 0;
        boardSize = sc.nextInt();
        Game game = new Game(boardSize);
        Snake snake = new Snake(0, 0, boardSize);
        while (true) {
            Thread.sleep(1000);
            game.print_state();
        }
    }
}
