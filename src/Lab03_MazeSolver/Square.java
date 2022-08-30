package Lab03_MazeSolver;

public class Square {
    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int START = 2;
    public static final int EXIT = 3;

    private int row;
    private int col;
    private int type;
    private char status;

    public Square(int row, int col, int type) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.status = '_';
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getType() {
        return type;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    private String getTypeSymbol() {
        if(type == EMPTY) return "_";
        if(type == WALL) return "#";
        if(type == START) return "S";
        return "E";
    }

    @Override
    public boolean equals(Object obj) {
        Square other = (Square) obj;
        return other.row == row && other.col == col;
    }

    @Override
    public String toString() {
        return type == EMPTY ? String.valueOf(status) : getTypeSymbol();
    }
}
