package Lab27_DynamicProgramming;

public class MinimumCoins {
    public static void main(String[] args) {
        System.out.println(minCoins(11, new int[]{9, 6, 5, 1}));
        System.out.println(minCoins(20, new int[]{9, 6, 5, 1}));
    }

    static int minCoins(int n, int[] coins) {
        if(n < 0) return -1;
        if(n == 0) return 0;

        int[] dp = new int[n + 1];
        dp[0] = 0;

        for(int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if(i - coin >= 0) min = Math.min(min, dp[i - coin] + 1);
            }

            dp[i] = min;
        }

        return dp[n];
    }
}
