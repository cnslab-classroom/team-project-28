import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Snake extends JFrame implements KeyListener {
    private Vector<Point> body;
    private char direction; // 방향을 나타내는 변수
    private int boardSize; // 게임판 크기 (NxN)
    private int speed;

    public Snake(int startX, int startY, int boardSize) {
        this.body = new Vector<Point>();
        this.body.add(new Point(startX, startY)); // 초기 뱀의 머리 위치
        this.direction = 'd'; // 기본 방향: 오른쪽
        this.boardSize = boardSize;
        this.speed = 300;

        // JFrame 설정
        setTitle("Snake Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this); // 키 리스너 추가
        setVisible(true); // 프레임 보이기
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 키 입력을 처리하는 메서드
        char newDirection = e.getKeyChar();
        if ("wasd".indexOf(newDirection) != -1) {
            direction = newDirection; // 방향 변경
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // keyPressed는 사용하지 않지만 필요함
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // keyReleased는 사용하지 않지만 필요함
    }

    public int getSpeed() {
        return speed;
    }

    void setSpeed(int speed) {
        if (speed < 100) {
            return;
        }
        this.speed = speed;
    }

    public char getDirection() {
        return direction;
    }

    public Vector<Point> getBody() {
        return body;
    }

    public boolean oneStep() {
        Point head = new Point(body.get(0).x, body.get(0).y);

        if (direction == 'w') {
            head.x--;
        } else if (direction == 'a') {
            head.y--;
        } else if (direction == 's') {
            head.x++;
        } else if (direction == 'd') {
            head.y++;
        }
        if (isValidPoint(head)) {
            // 꼬리 제거
            body.remove(body.size() - 1);
            // 새로운 머리 추가
            body.add(0, head);
            return true;
        } else
            return false;

    }

    private boolean isValidPoint(Point p) {
        if (p.x < 0 || p.x >= boardSize || p.y < 0 || p.y >= boardSize * 2 || isCollision(p)) {
            return false;
        }
        return true;
    }

    boolean isCollision(Point p) {
        if (body.size() != 1 && p.x == body.get(1).x && p.y == body.get(1).y) {
            return false;
        }
        for (int i = 0; i < body.size() - 1; i++) {
            if (body.get(i).equals(p)) {
                return true;
            }
        }
        return false;
    }

    public void getNewTail() {
        Point tail = new Point(body.get(body.size() - 1).x, body.get(body.size() - 1).y);

        // tail오른쪽
        tail.y++;
        if (isValidPoint(tail)) {
            body.add(tail);
        } else {
            // tail아래
            tail.y--;
            tail.x++;
            if (isValidPoint(tail)) {
                body.add(tail);
            } else {
                // tail왼쪽
                tail.x--;
                tail.y--;
                if (isValidPoint(tail)) {
                    body.add(tail);
                } else {
                    // tail위
                    tail.y++;
                    tail.x--;
                    if (isValidPoint(tail)) {
                        body.add(tail);
                    }
                }
            }
        }

    }

}
