package Lab10_Snowflake;

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
        if(a / 10 == 0 || b / 10 == 0) return a % 10 == b % 10 ? 1 : 0;
        return (a % 10 == b % 10 ? 1 : 0) + matchingDigits(a / 10, b / 10);
    }

    void doubleUp(Stack<Integer> nums) {
        if(nums.isEmpty()) return;

        int x = nums.pop();
        doubleUp(nums);
        nums.push(x);
        nums.push(x);
    }

    void printThis(int n) {
        if(n == 1) {
            System.out.print("*");
            return;
        }

        if(n == 2) {
            System.out.print("**");
            return;
        }

        System.out.print("<");
        printThis(n - 2);
        System.out.print(">");
    }

    void printNums2(int n) {
        if(n == 1) {
            System.out.print("1 ");
            return;
        }

        if(n == 2) {
            System.out.print("1 1 ");
            return;
        }

        System.out.print((n + 1) / 2 + " ");
        printNums2(n - 2);
        System.out.print((n + 1) / 2 + " ");
    }
}
