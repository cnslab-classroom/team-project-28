import java.util.Scanner;
import java.util.Vector;

public class Game {
    int boardSize;       // 보드의 세로 크기
    int boardSize_y;     // 보드의 가로 크기
    char[][] board;      // 보드를 표현하는 2D 배열
    Item item;           // 아이템 객체

    Game(int boardSize) {
        this.boardSize = boardSize;
        this.boardSize_y = boardSize * 2;
        this.board = new char[boardSize][boardSize * 2];
        this.item = new Item(boardSize); // 아이템 초기화

        // 보드 초기화
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize * 2; j++) {
                board[i][j] = '□';
            }
        }
    }

    // 현재 보드 상태를 출력하는 메서드
    void print_state() {
        System.out.print("\033[H\033[2J"); // 터미널 화면을 지움
        System.out.flush();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize_y; j++) {
                if (i == item.getX() && j == item.getY()) {
                    System.out.print('i'); // 아이템 표시
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.println();
        }
    }

    // 보드 상태를 업데이트하는 메서드
    void update_state(Snake snake) {
        Vector<Point> v = snake.getBody();
        
        // 보드 초기화
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize_y; j++) {
                board[i][j] = '□';
            }
        }

        // 뱀의 몸체 표시
        for (int i = 0; i < v.size(); i++) {
            Point p = v.get(i);
            board[p.x][p.y] = '■';
        }
    }

    // 메인 메서드
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("보드 크기를 입력하세요: ");
        int boardSize = sc.nextInt();

        Game game = new Game(boardSize);  // 게임 객체 생성
        Snake snake = new Snake(0, 0, boardSize);  // 뱀 객체 생성

        while (true) {
            boolean ateItem = snake.oneStep(game.item); // 뱀이 아이템을 먹었는지 확인

            if (ateItem) {
                snake.grow(); // 뱀이 아이템을 먹으면 길이 증가
                game.item.spawnNewItem(); // 새로운 아이템 생성
            }

            // 충돌로 이동 불가능하면 게임 종료
            if (!snake.canMove()) {
                System.out.println("게임 종료! 뱀이 충돌했습니다.");
                break;
            }

            // 보드 상태 업데이트 및 출력
            game.update_state(snake);
            game.print_state();

            Thread.sleep(1000); // 게임 속도 조절
        }

        sc.close(); // 스캐너 종료
    }
}
