import java.util.Vector;

public class SpeedItem extends Item {
    public char image = 'S';
    public SpeedItem(int boardSize, Vector<Point> snakeBody) {
        super(boardSize, snakeBody);
    }
}
