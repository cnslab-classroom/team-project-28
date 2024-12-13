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
        System.out.flush();
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
        // 사용자에게 보드 크기값을 받아 보드 생성
        Scanner sc = new Scanner(System.in);
        System.out.print("보드 크기를 입력하세요: ");
        int boardSize = sc.nextInt();
        
        Game game = new Game(boardSize);
        Snake snake = new Snake(0, 0, boardSize);
        
        while (true) {
            // 뱀 이동
            boolean canMove = snake.oneStep();
            
            // 충돌로 인해 이동 불가 시 게임 종료
            if (!canMove) {
                System.out.println("게임 종료! 뱀이 충돌했습니다.");
                break;
            }
            
            // 상태 업데이트 및 출력
            game.update_state(snake);
            game.print_state();
            
            // 게임 속도 조절
            Thread.sleep(1000);
        }
        
        sc.close(); // 스캐너 닫기
    }
    
}