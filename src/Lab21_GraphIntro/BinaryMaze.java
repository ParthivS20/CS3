package Lab21_GraphIntro;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryMaze {
    final static int[] dx = {-1, 0, 1, 0};
    final static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new PackageFile("data/maze.dat", BinaryMaze.class));

        int R = input.nextInt();
        int C = input.nextInt();
        int T = input.nextInt();
        int[][] maze = new int[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                maze[r][c] = input.nextInt();
            }
        }

        for (int i = 0; i < T; i++) {
            int distance = bfs(new Location(input.nextInt(), input.nextInt()), new Location(input.nextInt(), input.nextInt()), maze);
            System.out.println(distance);
        }
    }

    public static int bfs(Location source, Location dest, int[][] maze) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        Queue<Location> queue = new LinkedList<>();
        queue.add(source);

        visited[source.row][source.col] = true;
        int distance = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Location temp = queue.remove();
                if(temp.equals(dest)) return distance;

                for(int j = 0; j < 4; j++) {
                    int r = temp.row + dx[j];
                    int c = temp.col + dy[j];

                    if(r >= 0 && r < maze.length && c >= 0 && c < maze[0].length && maze[r][c] == 1 && !visited[r][c]) {
                        queue.add(new Location(r, c));
                        visited[r][c] = true;
                    }
                }
            }

            distance++;
        }

        return -1;
    }

    static class Location {
        int row;
        int col;

        Location(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null) return false;
            if(obj == this) return true;
            if(!(obj instanceof Location)) return false;

            Location l = (Location) obj;

            return this.row == l.row && this.col == l.col;
        }
    }
}
