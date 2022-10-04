package Lab03_MazeSolver;

public class Square {
    //These values are used to denote what type of Square this is in the maze
    public final static int EMPTY = 0; //an empty space
    public final static int WALL  = 1; //a wall
    public final static int START = 2; //the start
    public final static int EXIT  = 3; //the exit

    //These values indicate the status of a particular Square while the maze is being solved, for the GUI
    public final static char WORKING      = 'o'; //currently on the work list (the stack)
    public final static char EXPLORED     = '.'; //done, already explored
    public final static char ON_EXIT_PATH = 'x'; //on the exit path, used in a later lab
    public final static char UNKNOWN      = '_'; //not known / visited yet (empty cells only)

    private int  row, col; //r, c location in the maze
    private int  type;     //type of this square, e.g. empty, wall, etc.
    private char status;   //the status of a room being explored, shown by the GUI

    Square(int row, int col, int type) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.status = UNKNOWN;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    int getType() {
        return type;
    }

    char getStatus() {
        return status;
    }

    boolean isExplored() {
        return status == EXPLORED;
    }

    void reset() {
        setStatus(UNKNOWN);
    }

    public void setStatus(char status) {
        this.status = status;
    }

    private char getTypeSymbol() {
        switch(type) {
            default:
            case EMPTY: return '_';
            case WALL: return '#';
            case START: return 'S';
            case EXIT: return 'E';
        }
    }

    @Override
    public boolean equals(Object obj) {
        Square other = (Square) obj;
        return other.row == row && other.col == col;
    }

    @Override
    public String toString() {
        return String.valueOf(type == EMPTY ? status : getTypeSymbol());
    }
}
