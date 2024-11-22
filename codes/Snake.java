import java.util.Vector;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.*;

class Point{
    public int x;
    public int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Snake {
    // 뱀의 몸을 저장하는 벡터, 1개마다 Point클래스저장하고 이는 x,y를 표현
    private Vector<Point> body; 
    private char direction; // w,s,a,d
    private int boardSize; // 게임판 크기 (NxN)

    // 생성자
    public Snake(int startX, int startY, int boardSize) {
        this.body = new Vector<Point>();
        this.body.add(new Point(0,0));// 초기 뱀의 머리 위치
        this.direction = 'd'; // 기본 방향: 오른쪽
        this.boardSize = boardSize;
    }

    // 방향 변경 메서드
    public void changeDirection() {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Future<Integer> future = executor.submit(() -> {
        try {
            return System.console().reader().read();
        } catch (IOException e) {
            return -1;
        }
    });

    try {
        int input = future.get(1, TimeUnit.SECONDS);
        if (input != -1) {
            char newDirection = (char) input;
            if ("wasd".indexOf(newDirection) != -1) {
                direction = newDirection;
            }
        }
    } catch (TimeoutException e) {
        // 시간 초과 시 아무 것도 하지 않음
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        executor.shutdownNow();
    }
}

    // 이동 메서드
    public boolean move() {
        int[] head = body.getFirst(); // 현재 머리 위치
        int[] newHead = new int[]{head[0] + direction[0], head[1] + direction[1]};

        // 게임판 밖으로 나갔는지 확인
        if (newHead[0] < 0 || newHead[1] < 0 || 
            newHead[0] >= boardSize || newHead[1] >= boardSize) {
            return false; // 게임 종료
        }

        // 자신의 몸과 충돌 확인
        for (int[] part : body) {
            if (newHead[0] == part[0] && newHead[1] == part[1]) {
                return false; // 게임 종료
            }
        }

        body.addFirst(newHead); // 새로운 머리 추가
        body.removeLast(); // 꼬리 제거
        return true; // 정상적으로 이동
    }

    // // 먹이를 먹었을 때 길이 증가
    // public void grow() {
    //     int[] tail = body.getLast(); // 꼬리 부분 복사
    //     body.addLast(new int[]{tail[0], tail[1]}); // 꼬리를 연장
    // }

    // // 뱀의 현재 위치 반환
    // public LinkedList<int[]> getBody() {
    //     return body;
    // }

    // // 뱀의 길이 반환
    // public int getLength() {
    //     return body.size();
    // }

    public char getDirection() {
        return direction;
    }
    public static void main(String[] args) {
        Snake snake = new Snake(0, 0, 10);

        for(int i =0; i<10 ;i++)  {
            snake.changeDirection();
            System.out.println("Current direction: " + snake.getDirection());
        }
    }
}
