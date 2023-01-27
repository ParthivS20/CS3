package Lab11_ForestFire;

import java.util.Arrays;

class RecursiveBacktrackingProbsRunner {
    public static void main(String[] args) {
        RecursiveBacktrackingProbs probs = new RecursiveBacktrackingProbs();

        System.out.print("printBinary(3) >>> ");
        probs.printBinary(3);
        System.out.println();

        System.out.println("clubStairs(4) >>");
        probs.climbStairs(4);

        System.out.println("campsite(2, 1) >>>");
        probs.campsite(2, 1);

        System.out.println("getMax(Arrays.asList(7, 30, 8, 22, 6, 1, 14), 19) >>> " + probs.getMax(Arrays.asList(7, 30, 8, 22, 6, 1, 14), 19));

        System.out.println("makeChange(25) >>> " + probs.makeChange(25));
        System.out.println("makeChange(100) >>> " + probs.makeChange(100));

        System.out.println("printMakeChange(11) >>>");
        probs.printMakeChange(11);

        System.out.println("longestCommonSub(\"ABCDEFG\", \"BGCEHAF\") >>> " + probs.longestCommonSub("ABCDEFG", "BGCEHAF"));
        System.out.println("longestCommonSub(\"12345\", \"54321 21 54321\") >>> " + probs.longestCommonSub("12345", "54321 21 54321"));
    }
}