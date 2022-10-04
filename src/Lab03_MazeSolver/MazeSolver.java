package Lab03_MazeSolver;

public abstract class MazeSolver {
    private final Maze maze;

    public MazeSolver(Maze maze) {
        this.maze = maze;
        makeEmpty();
        add(maze.getStart());
    }

    public abstract void makeEmpty();

    public abstract boolean isEmpty();

    public abstract void add(Square s);

    public abstract Square next();

    public boolean isSolved() {
        return maze.isSolved();
    }

    public void step() {
        if(isEmpty()) return;

        Square n = next();
        if(n.equals(maze.getExit())) {
            maze.setSolved(true);
            return;
        }

        for(Square s : maze.getNeighbors(n)) {
            if((s.getType() == Square.EMPTY || s.getType() == Square.EXIT) && s.getStatus() == Square.UNKNOWN) {
                add(s);
                s.setStatus(Square.WORKING);
            }
        }

        n.setStatus(Square.EXPLORED);
    }

    public String getPath() {
        if (isEmpty()) return "Maze cannot be solved :(";
        if (isSolved()) return "Maze is solved!";
        return "Solving...";
    }

    void solve() {
        while(!isSolved()) {
            step();
        }
    }
}
