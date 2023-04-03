package Lab27_DynamicProgramming;

public class RodCutting {
    public static void main(String[] args) {
        System.out.println(maxRevenue(4, new int[]{0, 1, 5, 8, 9}));
    }

    static int maxRevenue(int n, int[] prices) {
        if(n < 0) return -1;
        if(n == 0) return 0;

        int[] dp = new int[n + 1];
        dp[0] = 0;

        for(int i = 1; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for(int j = 1; j <= i; j++) {
                max = Math.max(max, prices[j] + dp[i - j]);
            }
            dp[i] = max;
        }

        return dp[n];
    }
}
