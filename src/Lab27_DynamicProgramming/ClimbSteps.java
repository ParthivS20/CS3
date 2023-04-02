package Lab27_DynamicProgramming;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClimbSteps {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new PackageFile("data/steps.dat", ClimbSteps.class));

        int T = input.nextInt();
        for(int i = 0; i < T; i++) {
            int n = input.nextInt();
            System.out.println(climb(n));
        }
    }

    static int climb(int n) {
        if(n == 0 || n == 1) return 1;

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        return dp[n];
    }
}
