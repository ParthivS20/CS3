package Lab22_ShortestPath;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class DijkstraRunner {
    public static void main(String[] args) throws FileNotFoundException {
        Dijkstra dijkstra = new Dijkstra(new Graph(new Scanner(new PackageFile("data/input6.txt", Graph.class))));

        System.out.println(dijkstra.distance(0, 5));
    }
}
