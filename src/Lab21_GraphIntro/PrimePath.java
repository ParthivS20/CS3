package Lab21_GraphIntro;

import java.util.Scanner;

public class PrimePath {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int startNum = input.nextInt();
        int endNum = input.nextInt();

        assert startNum / 1000 >= 1 && endNum / 1000 >= 1;

        System.out.println(shortestPath(startNum, endNum));
    }

    private static int shortestPath(int startNum, int endNum) {
        boolean[] primes = new boolean[1000];
        for(int i = 1000; i < 10000; i++) {
            if(isPrime(i)) primes[i % 1000] = true;
        }
        System.out.println(primes[0]);
        return 1;
    }

    private static boolean isPrime(int n) {
        if (n == 2 || n == 3) return true;
        if (n <= 1 || n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }

        return true;
    }

    private static boolean differsByOneDigit(int n1, int n2) {
        int count = 0;
        while(n1 > 0 && n2 > 0) {
            if(n1 % 10 != n2 % 10) count++;
            n1 /= 10;
            n2 /= 10;
        }

        return count == 1;
    }
}
