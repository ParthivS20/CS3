package Lab03_MazeSolver;

public class Square {
    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int START = 2;
    public static final int EXIT = 3;

    private int type;
    private int row;
    private int col;

    public Square(int row, int col, int type) {

    }

    @Override
    public String toString() {
        return "";
    }
}
