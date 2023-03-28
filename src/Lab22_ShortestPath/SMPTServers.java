package Lab22_ShortestPath;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.*;

public class SMPTServers {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new PackageFile("data/smpt.txt", SMPTServers.class));

        int N = input.nextInt();
        for(int i = 0; i < N; i++) {
            int n = input.nextInt();
            int m = input.nextInt();
            int S = input.nextInt();
            int T = input.nextInt();

            Vertex[] matrix = new Vertex[n];
            for(int j = 0; j < n; j++) {
                matrix[j] = new Vertex(j);
            }

            for(int j = 0; j < m; j++) {
                int a = input.nextInt();
                int b = input.nextInt();
                int w = input.nextInt();

                matrix[a].addEdge(matrix[b]);
                matrix[b].addEdge(matrix[a]);

                matrix[a].edges.get(matrix[a].edges.size() - 1).distance = w;
                matrix[b].edges.get(matrix[b].edges.size() - 1).distance = w;
            }

            double ms = dijkstra(S, T, matrix);
            System.out.println("Case #" + i + ": " + (ms == Double.POSITIVE_INFINITY ? "unreachable" : ms));
        }
    }

    private static double dijkstra(int source, int destination, Vertex[] matrix) {
        Queue<Vertex> pq = new PriorityQueue<>();
        matrix[source].distance = 0;
        pq.offer(matrix[source]);

        while (!pq.isEmpty()) {
            Vertex temp = pq.poll();

            if (temp.id == destination) return temp.distance;
            for (Vertex edge : temp.edges) {
                double nextDist = temp.distance + getWeight(temp, edge);
                if (nextDist < edge.distance) {
                    edge.distance = nextDist;
                    pq.add(edge);
                }
            }
        }

        return Double.POSITIVE_INFINITY;
    }

    public static double getWeight(Vertex a, Vertex b) {
        for (Vertex edge : a.edges) {
            if (edge.id == b.id) return edge.distance;
        }

        return Double.POSITIVE_INFINITY;
    }

    private static class Vertex implements Comparable<Vertex> {
        int id;
        double distance;
        List<Vertex> edges;

        Vertex(int id) {
            this.id = id;
            this.distance = Double.POSITIVE_INFINITY;
            this.edges = new ArrayList<>();
        }

        void addEdge(Vertex v) {
            edges.add(v);
        }

        public int compareTo(Vertex other) {
            return Double.compare(this.distance, other.distance);
        }
    }
}
