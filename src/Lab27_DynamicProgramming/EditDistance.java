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

        int m = str1.length();
        int n = str2.length();

        int[][] opt = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) opt[i][0] = i;
        for (int j = 0; j <= n; j++) opt[0][j] = j;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 0) {
                    opt[i][j] = j;
                } else if (j == 0) {
                    opt[i][j] = i;
                } else {
                    opt[i][j] = min(opt[i - 1][j] + 2, opt[i][j - 1] + 2, opt[i - 1][j - 1] + penalty(str1.charAt(i - 1), str2.charAt(j - 1)));
                }
            }
        }

        System.out.println("Edit distance = " + opt[m][n]);
    }

    static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    static int penalty(char a, char b) {
        if (a == '-' || b == '-') return 2;
        else if (a == b) return 0;
        return 1;
    }
}
