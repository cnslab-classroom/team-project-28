import java.util.Vector;

public class Obstacle extends Item {
    public char image = 'X';
    public Obstacle(int boardSize, Vector<Point> snakeBody) {
        super(boardSize, snakeBody);
    }
}
