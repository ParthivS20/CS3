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

        /*
        long start = System.currentTimeMillis();
        System.out.println(climb(200000000));
        System.out.println(System.currentTimeMillis() - start + "ms");

        start = System.currentTimeMillis();
        System.out.println(climb2(200000000));
        System.out.println(System.currentTimeMillis() - start + "ms");
        */
    }

    static int climb(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        int[] dp = new int[3];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            int temp1 = dp[2];
            int temp2 = dp[1];
            dp[2] = dp[2] + dp[1] + dp[0];
            dp[1] = temp1;
            dp[0] = temp2;
        }

        return dp[2];
    }
}
