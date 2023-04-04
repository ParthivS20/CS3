package Lab26_Thievery;

import java.util.HashMap;
import java.util.Map;

public class Memoization {
    static long fibonacci(int n) {
        if(n == 0) return 0;
        if(n <= 2) return 1;

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    static long fibonacciMemo(int n) {
        HashMap<Integer, Long> memo = new HashMap<>();
        return fibonacciMemo(n, memo);
    }

    private static long fibonacciMemo(int n, HashMap<Integer, Long> memo) {
        if(n == 0) return 0;
        if(n <= 2) return 1;

        if(memo.containsKey(n)) return memo.get(n);

        long value = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        memo.put(n, value);
        return value;
    }

    static int pascal(int row, int col) {
        if(col == 0 || col == row) return 1;
        return pascal(row - 1, col - 1) + pascal(row - 1, col);
    }

    static int pascalMemo(int row, int col) {
        HashMap<Pair, Integer> memo = new HashMap<>();
        return pascalMemo(row, col, memo);
    }

    private static int pascalMemo(int row, int col, HashMap<Pair, Integer> memo) {
        if(col == 0 || col == row) return 1;

        Pair pair = new Pair(row, col);
        if(memo.containsKey(pair)) return memo.get(pair);

        int value = pascalMemo(row - 1, col - 1, memo) + pascalMemo(row - 1, col, memo);
        memo.put(pair, value);
        return value;
    }

    static int numPaths(int[][] grid) {
        Integer[][] memo = new Integer[grid.length][grid[0].length];
        return numPaths(grid, 0, 0, memo);
    }

    private static int numPaths(int[][] grid, int row, int col, Integer[][] memo) {
        if(row == grid.length - 1 && col == grid[0].length - 1) return 1;
        if(row >= grid.length || col >= grid[0].length) return 0;
        if(grid[row][col] == 1) return 0;

        if(memo[row][col] != null) return memo[row][col];

        int value = numPaths(grid, row + 1, col, memo) + numPaths(grid, row, col + 1, memo);
        memo[row][col] = value;
        return value;
    }

    static int minCoins(int total, int[] coins) {
        Map<Integer, Integer> memo = new HashMap<>();
        return minCoins(total, coins, memo);
    }

    private static int minCoins(int total, int[] coins, Map<Integer, Integer> memo) {
        if(total == 0) return 0;
        if(memo.containsKey(total)) return memo.get(total);

        int min = Integer.MAX_VALUE;
        for(int coin : coins) {
            if(total - coin >= 0) {
                min = Math.min(min, minCoins(total - coin, coins, memo));
            }
        }

        memo.put(total, min + 1);
        return min + 1;
    }

    static int rob(int[] nums) {
        if(nums.length == 0) return 0;

        Map<Integer, Integer> memo = new HashMap<>();
        return rob(nums, 0, memo);
    }

    private static int rob(int[] nums, int index, Map<Integer, Integer> memo) {
        if(index >= nums.length) return 0;
        if(memo.containsKey(index)) return memo.get(index);

        int max = Math.max(rob(nums, index + 1, memo), nums[index] + rob(nums, index + 2, memo));
        memo.put(index, max);
        return max;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(fibonacci(45));
        System.out.println("Fibonacci took " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(fibonacciMemo(45));
        System.out.println("FibonacciMemo took " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(pascal(30, 20));
        System.out.println("Pascal took " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(pascalMemo(30, 20));
        System.out.println("PascalMemo took " + (System.currentTimeMillis() - start) + "ms");

        System.out.println("numPaths(new int[10][10]) >>> " + numPaths(new int[10][10]));

        System.out.println("minCoins(11, new int[]{9, 6, 5, 1}) >>> " + minCoins(11, new int[]{9, 6, 5, 1}));

        System.out.println(rob(new int[]{200,234,182,111,87,194,221,217,71,162,140,51,81,80,232,193,223,103,139,103}));
    }

    static class Pair {
        private final int row;
        private final int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair p = (Pair) o;
            return row == p.row && col == p.col;
        }

        @Override
        public int hashCode() {
            return 31 * row + col;
        }
    }
}

