import java.util.Scanner;
import java.lang.Thread;
import java.util.Vector;

public class Game {
    int boardSize;
    int boardSize_y;
    char[][] board;

    Game(int boardSize) {
        this.boardSize = boardSize;
        this.boardSize_y = boardSize*2;
        this.board = new char[boardSize][boardSize*2];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '□';
            }
            System.out.println();
        }
    }

    void print_state() {
        
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize_y; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    // snake의 위치를 받아서 board에 표시
    void update_state(Snake snake) {
        Vector<Point> v = snake.getBody();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize_y; j++) {
                board[i][j] = '□';
            }
            System.out.println();
        }
        for (int i = 0; i < v.size(); i++) {
            Point p = v.get(i);
            board[p.x][p.y] = '■';
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
            snake.oneStep();
            game.update_state(snake);
            game.print_state();
           
            Thread.sleep(1000);
            
        }
    }
}
