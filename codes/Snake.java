import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class Snake extends JFrame implements KeyListener {
    private Vector<Point> body; // 뱀의 몸을 구성하는 점들의 리스트
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
        return direction; // 현재 방향 반환
    }

    public Vector<Point> getBody() {
        return body; // 뱀의 몸을 구성하는 점들의 리스트 반환
    }

    // 뱀이 한 칸 이동하는 메서드
    public boolean oneStep(Item item) {
        Point head = new Point(body.get(0).x, body.get(0).y); // 현재 머리 위치 복사

        // 방향에 따라 머리 위치 업데이트
        if (direction == 'w') head.x--;
        else if (direction == 'a') head.y--;
        else if (direction == 's') head.x++;
        else if (direction == 'd') head.y++;

        if (isValidPoint(head)) { // 새로운 머리 위치가 유효한지 확인
            if (head.x == item.getX() && head.y == item.getY()) {
                return true; // 아이템을 먹음
            }

            // 꼬리 제거 후 머리 추가
            body.remove(body.size() - 1);
            body.add(0, head);
            return false;
        } else {
            return false; // 충돌 발생
        }
    }

    // 뱀의 길이를 증가시키는 메서드
    public void grow() {
        Point tail = body.get(body.size() - 1); // 꼬리 위치 가져오기
        body.add(new Point(tail.x, tail.y)); // 꼬리 위치에 새 점 추가
    }

    // 새로운 머리 위치가 유효한지 확인하는 메서드
    private boolean isValidPoint(Point p) {
        // 보드 경계 및 자기 자신과의 충돌 체크
        if (p.x < 0 || p.x >= boardSize || p.y < 0 || p.y >= boardSize * 2 || body.contains(p)) {
            return false;
        }
        return true;
    }

    // 뱀이 현재 방향으로 이동할 수 있는지 확인하는 메서드
    public boolean canMove() {
        Point head = new Point(body.get(0).x, body.get(0).y); // 현재 머리 위치 복사

        // 방향에 따라 머리 위치 업데이트
        if (direction == 'w') head.x--;
        else if (direction == 'a') head.y--;
        else if (direction == 's') head.x++;
        else if (direction == 'd') head.y++;

        return isValidPoint(head); // 유효한 위치인지 확인
    }
}
