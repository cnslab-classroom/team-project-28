import java.util.Scanner;
import java.io.File;
import java.lang.Thread;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game {
    int boardSize;
    int boardSize_y;
    char[][] board;

    Game(int boardSize) {
        this.boardSize = boardSize;
        this.boardSize_y = boardSize * 2;
        this.board = new char[boardSize][boardSize * 2];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '□';
            }
            System.out.println();
        }
    }

    // 현재상태를 출력
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
    void update_state(Snake snake, Food food, Obstacle obstacle, SpeedItem speedItem) {
        Vector<Point> v = snake.getBody();
        Vector<Point> v2 = new Vector<Point>();

        for (int i = 0; i < v.size(); i++) {
            v2.add(v.get(i));
        }
        v2.add(new Point(food.x, food.y));
        v2.add(new Point(obstacle.x, obstacle.y));
        v2.add(new Point(speedItem.x, speedItem.y));

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

        if (food.x == snake.getBody().get(0).x && food.y == snake.getBody().get(0).y) {
            snake.getNewTail();
            food.spawnNewItem(v2);
            board[food.x][food.y] = food.image;

        }
        if (obstacle.x == snake.getBody().get(0).x && obstacle.y == snake.getBody().get(0).y) {
            System.out.println("Game Over");
            System.exit(0);
        }
        if (speedItem.x == snake.getBody().get(0).x && speedItem.y == snake.getBody().get(0).y) {
            snake.setSpeed(snake.getSpeed() - 100);
            speedItem.spawnNewItem(v2);
            board[food.x][food.y] = speedItem.image;
        }

        board[food.x][food.y] = food.image;
        board[obstacle.x][obstacle.y] = obstacle.image;
        board[speedItem.x][speedItem.y] = speedItem.image;

    }

    public static void main(String[] args) throws Exception {

        File audioFile = new File("team-project-28\\jogang.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        // 배경음악 시작
        clip.loop(Clip.LOOP_CONTINUOUSLY); // 반복 재생

        // 사용자에게 board크기값을 받아 board를 생성
        Scanner sc = new Scanner(System.in);
        int boardSize = 0;
        System.out.print("Input board size>>");
        boardSize = sc.nextInt();
        Game game = new Game(boardSize);
        Snake snake = new Snake(0, 0, boardSize);

        // food의 위치 초기화
        Food food = new Food(boardSize, snake.getBody());
        food.spawnNewItem(snake.getBody());

        Obstacle obstacle = new Obstacle(boardSize, snake.getBody());
        obstacle.spawnNewItem(snake.getBody());

        SpeedItem speedItem = new SpeedItem(boardSize, snake.getBody());
        speedItem.spawnNewItem(snake.getBody());

        while (true) {
            if (!snake.oneStep()) {
                System.out.println("Game Over");
                return;
            }
            ;

            game.update_state(snake, food, obstacle, speedItem);
            game.print_state();

            Thread.sleep(snake.getSpeed());

        }
    }
}
