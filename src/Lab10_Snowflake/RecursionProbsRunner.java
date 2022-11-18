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

        System.out.print("printThis(1): ");
        probs.printThis(1);
        System.out.print("printThis(2): ");
        probs.printThis(2);
        System.out.print("printThis(3): ");
        probs.printThis(3);
        System.out.print("printThis(4): ");
        probs.printThis(4);
        System.out.print("printThis(5): ");
        probs.printThis(5);
        System.out.print("printThis(6): ");
        probs.printThis(6);
        System.out.print("printThis(7): ");
        probs.printThis(7);
        System.out.print("printThis(8): ");
        probs.printThis(8);
    }
}
