import java.util.Random;

public class Item {
    private int x;
    private int y;
    private int boardSize;

    public Item(int boardSize) {
        this.boardSize = boardSize;
        spawnNewItem();
    }

    public void spawnNewItem() {
        Random rand = new Random();
        x = rand.nextInt(boardSize);
        y = rand.nextInt(boardSize * 2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
