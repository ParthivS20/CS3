package Lab03_MazeSolver;

import Util.PackageFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
    private Square[][] grid;
    private Square start, exit;
    private boolean solved;

    public boolean loadMaze(String fileName) {
        Scanner file;
        try {
            file = new Scanner(new PackageFile("data/" + fileName, getClass()));
        } catch (IOException e) {
            System.out.println("File \"" + fileName +  "\" was not found");
            return false;
        }

        grid = new Square[file.nextInt()][file.nextInt()];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Square(i, j, file.nextInt());
                if(grid[i][j].getType() == Square.START) start = grid[i][j];
                if(grid[i][j].getType() == Square.EXIT) exit = grid[i][j];
            }
        }

        file.close();
        return true;
    }

    List<Square> getNeighbors(Square s) {
        List<Square> neighbors = new ArrayList<>();
        if(s.getRow() > 0) neighbors.add(grid[s.getRow() - 1][s.getCol()]);                     //north
        if(s.getCol() < grid[0].length - 1) neighbors.add(grid[s.getRow()][s.getCol() + 1]);        //east
        if(s.getRow() < grid.length - 1) neighbors.add(grid[s.getRow() + 1][s.getCol()]);       //south
        if(s.getCol() > 0) neighbors.add(grid[s.getRow()][s.getCol() - 1]);                     //west
        return neighbors;
    }

    Square getStart() {
        return start;
    }

    Square getExit() {
        return exit;
    }

    boolean isSolved() {
        return solved;
    }

    void setSolved(boolean solved) {
        this.solved = solved;
    }

    public void reset() {
        if(grid != null) {
            solved = false;
            for (Square[] squares : grid) {
                for (Square square : squares) {
                    square.reset();
                }
            }
        }
    }

    @Override
    public String toString() {
        String out = "";
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                out += grid[i][j].toString();
                if(j < grid[i].length - 1) out += " ";
            }
            if(i < grid.length - 1) out += "\n";
        }
        return out;
    }
}
