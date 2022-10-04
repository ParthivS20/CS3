package Lab08_LinkedListQueue;

import Lab03_MazeSolver.Maze;
import Lab03_MazeSolver.MazeSolver;
import Lab03_MazeSolver.Square;

public class MazeSolverQueue extends MazeSolver {
    private MyQueue<Square> worklist;

    public MazeSolverQueue(Maze maze) {
        super(maze);
    }

    @Override
    public void makeEmpty() {
        worklist = new MyQueue<>();
    }

    @Override
    public boolean isEmpty() {
        return worklist.isEmpty();
    }

    @Override
    public void add(Square s) {
        worklist.offer(s);
        s.setStatus(Square.EXPLORED);
    }

    @Override
    public Square next() {
        return worklist.poll();
    }
}
