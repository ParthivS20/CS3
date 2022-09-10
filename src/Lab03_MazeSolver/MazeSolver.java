package Lab03_MazeSolver;

import java.util.List;

public abstract class MazeSolver {
    private Maze maze;

    MazeSolver(Maze maze) {
        this.maze = maze;
        makeEmpty();
        add(maze.getStart());
    }

    abstract void makeEmpty();
    abstract boolean isEmpty();
    abstract void add(Square s);
    abstract Square next();

    boolean isSolved() {
        return maze.isSolved();
    }

    void step() {
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

    String getPath() {
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
