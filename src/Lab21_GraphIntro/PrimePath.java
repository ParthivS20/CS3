package Lab21_GraphIntro;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PrimePath {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int startNum = input.nextInt();
        int endNum = input.nextInt();

        assert startNum >= 1000 &&
                startNum < 10000 &&
                endNum >= 1000 &&
                endNum < 10000 &&
                isPrime(startNum) &&
                isPrime(endNum);

        System.out.println(bfs(startNum, endNum));
    }

    private static int bfs(int startNum, int endNum) {
        if (startNum == endNum) return 0;

        boolean[] primes = getPrimes();
        boolean[][] matrix = new boolean[10000][10000];

        for(int i = 1000; i < 10000; i++) {
            if(primes[i]) {
                for(int j = i + 1; j < 10000; j++) {
                    if(primes[j] && differsByOneDigit(i, j)) {
                        matrix[i][j] = true;
                        matrix[j][i] = true;
                    }
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[10000];

        queue.offer(startNum);
        visited[startNum] = true;

        int[] steps = new int[10000];

        while(!queue.isEmpty()) {
            int temp = queue.poll();
            if (temp == endNum) return steps[temp];

            for(int i = 1000; i < 10000; i++) {
                if (matrix[temp][i] && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    steps[i] = steps[temp] + 1;
                }
            }
        }

        return -1;
    }

    private static boolean isPrime(int n) {
        if (n == 2 || n == 3) return true;
        if (n <= 1 || n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }

        return true;
    }

    private static boolean[] getPrimes() {
        boolean[] primes = new boolean[10000];
        for (int i = 1000; i < 10000; i++) {
            if (isPrime(i)) primes[i] = true;
        }

        return primes;
    }

    private static boolean differsByOneDigit(int n1, int n2) {
        if (n1 == n2) return false;

        int count = 0;
        while(n1 > 0 && n2 > 0) {
            if (n1 % 10 != n2 % 10) {
                if (count++ > 1) return false;
            }

            n1 /= 10;
            n2 /= 10;
        }

        return count == 1;
    }
}
