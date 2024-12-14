import java.util.Random;
import java.util.Vector;

abstract public class Item {
    public int x;
    public int y;
    private int boardSize;

    public Item(int boardSize, Vector<Point> snakeBody) {
        this.boardSize = boardSize;
        spawnNewItem(snakeBody);
    }

    //snakeBody에 있는 좌표와 겹치지 않게 아이템을 생성
    public void spawnNewItem(Vector<Point> snakeBody) {
        Random rand = new Random();
        do {
            x = rand.nextInt(boardSize);
            y = rand.nextInt(boardSize * 2);
        } while (contain(snakeBody,new Point(x, y)));
    }

    private boolean contain(Vector<Point> snakeBody, Point p) {
        for(int i =0; i<snakeBody.size(); i++){
            if(snakeBody.get(i).equals(p)){
                return true;
            }
        }
        return false;
    }

   
}
