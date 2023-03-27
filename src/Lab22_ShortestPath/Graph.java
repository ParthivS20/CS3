package Lab22_ShortestPath;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
    int V;
    int E;

    Vertex[] matrix;

    Graph(Scanner input) {
        V = input.nextInt();
        E = input.nextInt();

        matrix = new Vertex[V];

        for (int i = 0; i < V; i++) {
            matrix[i] = new Vertex(input.nextInt(), input.nextInt(), input.nextInt());
        }

        for (int i = 0; i < E; i++) {
            int a = input.nextInt();
            int b = input.nextInt();

            matrix[a].addEdge(b);
            matrix[b].addEdge(a);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Graph g = new Graph(new Scanner(new PackageFile("data/input6.txt", Graph.class)));
        System.out.println(g);
    }

    double distance(int from, int to) {
        return matrix[from].distanceTo(matrix[to]);
    }

    @Override
    public String toString() {
        String out = "Vertices: " + V + " Edges: " + E + "\n";
        for (Vertex v : matrix) {
            out += v + "\n";
        }
        return out;
    }
}
