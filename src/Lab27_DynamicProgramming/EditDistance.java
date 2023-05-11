package Lab27_DynamicProgramming;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class EditDistance {
    private static final String FILENAME = "example10.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new PackageFile("data/genetic_sequences/" + FILENAME, EditDistance.class));

        String str1 = file.nextLine();
        String str2 = file.nextLine();

        int[][] opt = getEditDistance(str1, str2);
        System.out.println("Edit distance = " + opt[str1.length()][str2.length()]);
    }

    static int[][] getEditDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] opt = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    opt[i][j] = j;
                } else if (j == 0) {
                    opt[i][j] = i;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    opt[i][j] = opt[i - 1][j - 1];
                } else {
                    opt[i][j] = 1 + min(opt[i][j - 1], opt[i - 1][j] + 2, opt[i - 1][j - 1] + 2);
                }
            }
        }

        return opt;
    }

    static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    static int penalty(char a, char b) {
        if (a == b) return 0;
        if (a == '-' || b == '-') return 2;
        return 1;
    }
}
