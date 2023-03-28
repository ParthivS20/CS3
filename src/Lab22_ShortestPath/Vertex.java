package Lab22_ShortestPath;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
    final int x;
    final int y;
    final int id;

    List<Integer> edges;
    boolean visited;
    double distance;

    Vertex(int id, int x, int y) {
        this.x = x;
        this.y = y;
        this.id = id;

        edges = new ArrayList<>();
        visited = false;
        distance = Double.POSITIVE_INFINITY;
    }

    double distanceTo(Vertex vertex) {
        return Math.sqrt(Math.pow(vertex.x - this.x, 2) + Math.pow(vertex.y - this.y, 2));
    }

    void addEdge(int id) {
        edges.add(id);
    }

    @Override
    public int compareTo(Vertex other) {
        return Double.compare(this.distance, other.distance);
    }

    @Override
    public String toString() {
        return id + ": (" + x + ", " + y + ")";
    }
}
