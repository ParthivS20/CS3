import java.util.Stack;

public class RecursionProbs {
    double sumReciprocals(int n) {
        return n == 1 ? 1 : 1.0 / n + sumReciprocals(n - 1);
    }

    int productOfEvens(int n) {
        return n == 0 ? 1 : 2 * n * productOfEvens(n - 1);
    }

    String conversion(int num, int base) {
        return (num / base == 0 ? "" : conversion(num / base, base)) + num % base;
    }

    int matchingDigits(int a, int b) {
        return 0;
    }

    void doubleUp(Stack<Integer> nums) {
        if(nums.isEmpty()) return;

        int x = nums.pop();
        doubleUp(nums);
        nums.push(x);
        nums.push(x);
    }

    void printThis(int n) {
        printThis((int) (Math.ceil(n / 2.0) - 1), "<");
        System.out.print("*" + (n % 2 == 0 ? "*" : ""));
        printThis((int) (Math.ceil(n / 2.0) - 1), ">");
        System.out.println();
    }

    private void printThis(int n, String x) {
        if(n == 0) return;
        System.out.print(x);
        printThis(n - 1, x);
    }

    void printNums2(int n) {

    }

    private void printNums2(int n, int m) {
        if(n <= m) {
            System.out.print(n);
        }
    }
}
