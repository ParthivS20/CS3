package Lab27_DynamicProgramming;

public class Fibonnaci {
    public static void main(String[] args) {
        System.out.println(fibonacci(30));
    }

    static long fibonacci(int n) {
        if (n <= 0) return 0;

        long[] dp = new long[2];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            long temp = dp[1];
            dp[1] = dp[0] + dp[1];
            dp[0] = temp;
        }

        return dp[1];
    }

}
