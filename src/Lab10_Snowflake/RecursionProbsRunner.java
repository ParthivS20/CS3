package Lab10_Snowflake;

import java.util.Stack;

public class RecursionProbsRunner {
    public static void main(String[] args) {
        RecursionProbs probs = new RecursionProbs();

        System.out.println("sumReciprocals(10) >>> " + probs.sumReciprocals(10));

        System.out.println("productOfEvens(4) >>> " + probs.productOfEvens(4));

        System.out.println("conversion(10, 2) >>> " + probs.conversion(10, 2));
        System.out.println("conversion(1453, 8) >>> " + probs.conversion(1453, 8));

        System.out.println("matchingDigits(1000, 0) >>> " + probs.matchingDigits(1000, 0));
        System.out.println("matchingDigits(298892, 7892) >>> " + probs.matchingDigits(298892, 7892));

        Stack<Integer> s = new Stack<>();
        s.push(3);
        s.push(7);
        s.push(12);
        s.push(9);
        probs.doubleUp(s);
        System.out.println("doubleUp(3, 7, 12, 9) >>> " + s);

        for(int i = 1; i <= 8; i++) {
            System.out.print("printThis(" + i + "):\t");
            probs.printThis(i);
            System.out.println();
        }

        for(int i = 1; i <= 10; i++) {
            System.out.print("printNums2(" + i + "):\t");
            probs.printNums2(i);
            System.out.println();
        }
    }
}
