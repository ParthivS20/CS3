package Lab27_DynamicProgramming;

public class Fibonnaci {
    public static void main(String[] args) {
        System.out.println(fibonacci(6));
    }

    static long fibonacci(int n) {
        if (n < 0) return 0;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) dp[i] = dp[i - 1] + dp[i - 2];

        return dp[n];
    }

}
