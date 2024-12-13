import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        
    }
}

public class Snake extends JFrame implements KeyListener {
    private Vector<Point> body;
    private char direction; // 방향을 나타내는 변수
    private int boardSize; // 게임판 크기 (NxN)

    public Snake(int startX, int startY, int boardSize) {
        this.body = new Vector<Point>();
        this.body.add(new Point(startX, startY)); // 초기 뱀의 머리 위치
        this.direction = 'd'; // 기본 방향: 오른쪽
        this.boardSize = boardSize;

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

    public char getDirection() {
        return direction;
    }

   
}
