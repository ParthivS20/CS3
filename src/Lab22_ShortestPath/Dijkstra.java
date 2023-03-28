package Lab22_ShortestPath;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Dijkstra {
    private Graph graph;

    Dijkstra(Graph graph) {
        this.graph = graph;
    }

    double distance(int source, int destination) {
        dijkstra(source, destination);
        return graph.matrix[destination].distance;
    }

    private void dijkstra(int source, int destination) {
        Queue<Vertex> pq = new PriorityQueue<>();
        graph.matrix[source].distance = 0;
        pq.offer(graph.matrix[source]);

        while(!pq.isEmpty()) {
            Vertex temp = pq.poll();

            //System.out.println("Processing node " + temp.id + " (dist " + temp.distance + ")");
            temp.visited = true;

            for(int edgeIndex : temp.edges) {
                Vertex edge = graph.matrix[edgeIndex];
                if(!edge.visited) {
                    double nextDist = temp.distance + graph.distance(temp.id, edge.id);
                    if(nextDist < edge.distance) {
                        //System.out.println("\tLowering " + edge.id + " to " + nextDist);
                        edge.distance = nextDist;
                        pq.add(edge);
                    }
                }
            }
        }
    }
}
