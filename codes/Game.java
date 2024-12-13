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

    //현재상태를 출력
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
    void update_state(Snake snake, Food food) {
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
        if(food.x == snake.getBody().get(0).x && food.y == snake.getBody().get(0).y){
            snake.getNewTail();
            food.spawnNewItem(v);
            board[food.x][food.y] = food.image;
        }else{
            board[food.x][food.y] = food.image;
        }
    }

    public static void main(String[] args) throws Exception {
        // 사용자에게 board크기값을 받아 board를 생성
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
           
            Thread.sleep(400);
            
        }
    }
}
