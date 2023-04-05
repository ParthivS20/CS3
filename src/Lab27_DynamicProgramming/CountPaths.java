package Lab27_DynamicProgramming;

public class CountPaths {
    public static void main(String[] args) {
        System.out.println(numberOfPaths(3, 3));
    }

    static int numberOfPaths(int m, int n) {
        if(m <= 0 || n <= 0) return 0;
        if(m == 1 || n == 1) return 1;

        int[] dp = new int[n];
        for(int i = 0; i < n; i++) dp[i] = 1;

        for (int i = 1; i < m; i++) {
            int prev = 1;
            for (int j = 1; j < n; j++) {
                int temp = dp[j];
                dp[j] = dp[j] + dp[j - 1] + prev;
                prev = temp;
            }
        }

        return dp[n - 1];
    }
}
