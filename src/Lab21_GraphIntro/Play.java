package Lab21_GraphIntro;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Play {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new PackageFile("data/play.dat", Play.class));
        int N = Integer.parseInt(input.nextLine());

        for(int i = 0; i < N; i++) {
            int n = input.nextInt();
            int m = input.nextInt();
            int l = input.nextInt();

            boolean[][] matrix = new boolean[n][n];
            for(int j = 0; j < m; j++) {
                int x = input.nextInt() - 1;
                int y = input.nextInt() - 1;

                matrix[x][y] = true;
            }

            boolean[] knocked = new boolean[n];
            for(int j = 0; j < l; j++) {
                int z = input.nextInt() - 1;
                if(!knocked[z]) knockDown(z, matrix, knocked);
            }

            int count = 0;
            for(boolean b : knocked) {
                if(b) count++;
            }

            System.out.println(count);
        }
    }

    private static void knockDown(int domino, boolean[][] matrix, boolean[] knocked) {
        knocked[domino] = true;
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[domino][i] && !knocked[i]) knockDown(i, matrix, knocked);
        }
    }
}
