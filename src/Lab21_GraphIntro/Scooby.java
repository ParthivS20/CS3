package Lab21_GraphIntro;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scooby {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new PackageFile("data/scooby.dat", Scooby.class));
        int n = Integer.parseInt(input.nextLine());

        for(int i = 0; i < n; i++) {
            boolean[][] matrix = new boolean[26][26];
            String[] passages = input.nextLine().split(" ");

            for(String passage : passages) {
                matrix[passage.charAt(0) - 'A'][passage.charAt(1) - 'A'] = true;
                matrix[passage.charAt(1) - 'A'][passage.charAt(0) - 'A'] = true;
            }

            String route = input.nextLine();
            System.out.println(findPath(route.charAt(0), route.charAt(1), matrix, new boolean[26]) ? "yes" : "no");
        }
    }

    private static boolean findPath(char start, char end, boolean[][] matrix, boolean[] visited) {
        if(start == end || matrix[start - 'A'][end - 'A']) return true;

        visited[start - 'A'] = true;
        for(int i = 0; i < 26; i++) {
            if(matrix[start - 'A'][i] && !visited[i] && findPath((char) (i + 'A'), end, matrix, visited)) return true;
        }

        return false;
    }
}
