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

    //현재상태를 출력
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

    // snake의 위치를 받아서 board에 표시
    void update_state(Snake snake, Food food) {
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
        if(food.x == snake.getBody().get(0).x && food.y == snake.getBody().get(0).y){
            snake.getNewTail();
            food.spawnNewItem(v);
            board[food.x][food.y] = food.image;
        }else{
            board[food.x][food.y] = food.image;
        }
    }

    // 메인 메서드
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int boardSize = 0;
        System.out.print("Input board size>>");
        boardSize = sc.nextInt();
        Game game = new Game(boardSize);
        Snake snake = new Snake(0, 0, boardSize);

        //food의 위치 초기화
        Food food = new Food(boardSize, snake.getBody());
        food.spawnNewItem(snake.getBody());

        while (true) {
            if(!snake.oneStep()){
                System.out.println("Game Over");
                return ;
            };
            
            

            game.update_state(snake, food);
            game.print_state();

            Thread.sleep(1000); // 게임 속도 조절
        }

        sc.close(); // 스캐너 종료
    }
}
