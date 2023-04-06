package Lab27_DynamicProgramming;

public class Fibonnaci {
    public static void main(String[] args) {
        System.out.println(fibonacci(6));
    }

    static long fibonacci(int n) {
        if (n <= 0) return 0;

        long a = 0;
        long b = 1;

        for (int i = 2; i <= n; i++) {
            long sum = a + b;
            a = b;
            b = sum;
        }

        return b;
    }
}
