package Lab27_DynamicProgramming;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MaximumPathSum {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new PackageFile("data/triangle2.txt", MaximumPathSum.class));

        int n = input.nextInt();
        int[][] triangle = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                triangle[i][j] = input.nextInt();
            }
        }

        System.out.println(maxPathSum(triangle));
    }

    static int maxPathSum(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];

        for(int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];

            for(int j = 1; j < i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }

            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
        }

        int max = dp[n - 1][0];
        for(int i = 1; i < n; i++) max = Math.max(max, dp[n - 1][i]);
        return max;
    }
}
