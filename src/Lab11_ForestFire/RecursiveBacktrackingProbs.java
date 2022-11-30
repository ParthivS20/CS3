package Lab11_ForestFire;

import java.util.Arrays;
import java.util.List;

public class RecursiveBacktrackingProbs {
    void printBinary(int digits) {
        printBinary(digits, "");
    }

    private void printBinary(int digits, String soFar) {
         if(digits == 0) {
             System.out.print(soFar + " ");
             return;
         }

         printBinary(digits - 1, soFar + 0);
         printBinary(digits - 1, soFar + 1);
    }

    void climbStairs(int steps) {
        climbStairs(steps, "");
    }

    private void climbStairs(int steps, String soFar) {
        if(steps == 0) {
            System.out.println("\t" + soFar.substring(0, soFar.length() - 2));
            return;
        }

        climbStairs(steps - 1, soFar + "1, ");
        if(steps - 2 >= 0) {
            climbStairs(steps - 2, soFar + "2, ");
        }
    }

    void campsite(int x, int y) {
        campsite(x, y, "");
    }

    private void campsite(int x, int y, String path) {
        if(x == 0 && y == 0) {
            System.out.println("\t" + path.substring(0, path.length() - 1));
        }

        if(x > 0) {
            campsite(x - 1, y, path + "E ");
        }

        if(y > 0) {
            campsite(x, y - 1, path + "N ");
        }

        if(x > 0 && y > 0) {
            campsite(x - 1, y - 1, path + "NE ");
        }
    }

    int getMax(List<Integer> nums, int limit) {
        return 0;
    }

    int makeChange(int amount) {
        return makeChange(amount, 0);
    }

    private int makeChange(int amount, int counter) {
        return 0;
    }

    void printMakeChange(int amount) {
        printMakeChange(amount, 0);
    }

    private void printMakeChange(int amount, int counter) {

    }

    String longestCommonSub(String a, String b) {
        return "";
    }
}

class RecursiveBacktrackingProbsRunner {
    public static void main(String[] args) {
        RecursiveBacktrackingProbs probs = new RecursiveBacktrackingProbs();

        System.out.print("printBinary(3) >>> ");
        probs.printBinary(3);
        System.out.println();

        System.out.println("clubStairs(4) >>> ");
        probs.climbStairs(4);

        System.out.println("campsite(2, 1) >>> ");
        probs.campsite(2, 1);

        System.out.println("getMax(Arrays.asList(7, 30, 8, 22, 6, 1, 14), 19) >>> " + probs.getMax(Arrays.asList(7, 30, 8, 22, 6, 1, 14), 19));

        System.out.println("makeChange(25) >>> " + probs.makeChange(25));
        System.out.println("makeChange(100) >>> " + probs.makeChange(100));

        probs.printMakeChange(25);

        System.out.println("longestCommonSub(\"ABCDEFG\", \"BGCEHAF\") >>> " + probs.longestCommonSub("ABCDEFG", "BGCEHAF"));
        System.out.println("longestCommonSub(\"12345\", \"54321 21 54321\") >>> " + probs.longestCommonSub("12345", "54321 21 54321"));
    }
}