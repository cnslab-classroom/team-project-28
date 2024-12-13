import java.util.Vector;

public class Food extends Item {
    public char image = '★';
    public Food(int boardSize, Vector<Point> snakeBody) {
        super(boardSize, snakeBody);
    }
}
