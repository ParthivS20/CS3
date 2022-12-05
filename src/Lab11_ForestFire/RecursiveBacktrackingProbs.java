package Lab11_ForestFire;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class RecursiveBacktrackingProbs {
    int[] coins = {25, 10, 5, 1};

    void printBinary(int digits) {
        printBinary(digits, "");
    }

    private void printBinary(int digits, String binary) {
        if(digits == 0) {
            System.out.print(binary + " ");
            return;
        }

        printBinary(digits - 1, binary + 0);
        printBinary(digits - 1, binary + 1);
    }

    void climbStairs(int steps) {
        climbStairs(steps, "");
    }

    private void climbStairs(int steps, String sequence) {
        if(steps == 0) {
            System.out.println("\t" + sequence.substring(0, sequence.length() - 2));
            return;
        }

        climbStairs(steps - 1, sequence + "1, ");
        if(steps - 2 >= 0) climbStairs(steps - 2, sequence + "2, ");
    }

    void campsite(int x, int y) {
        campsite(x, y, "");
    }

    private void campsite(int x, int y, String path) {
        if(x == 0 && y == 0) {
            System.out.println("\t" + path.substring(0, path.length() - 1));
            return;
        }

        if(x > 0) campsite(x - 1, y, path + "E ");
        if(y > 0) campsite(x, y - 1, path + "N ");
        if(x > 0 && y > 0) campsite(x - 1, y - 1, path + "NE ");
    }

    int getMax(List<Integer> nums, int limit) {
        return getMax(nums, limit, 0, nums.size());
    }

    private int getMax(List<Integer> nums, int limit, int sum, int len) {
        return len == 0 ? (sum <= limit ? sum : -1) : Math.max(getMax(nums, limit,sum + nums.get(len - 1), len - 1), getMax(nums, limit, sum, len - 1));
    }

    int makeChange(int amount) {
        return makeChange(amount, 0);
    }

    private int makeChange(int amount, int i) {
        if(i >= coins.length) return 0;
        if(amount <= 0) return amount == 0 ? 1 : 0;

        while(coins[i] > amount) i++;

        return makeChange(amount - coins[i], i) + makeChange(amount, i + 1);
    }

    void printMakeChange(int amount) {
        System.out.println(" P  N  D  Q");
        System.out.println("------------");

        LinkedHashSet<String> solutions = new LinkedHashSet<>();
        printMakeChange(amount, 0, new int[coins.length], solutions);

        for(String s : solutions) {
            System.out.println(s);
        }
    }

    private void printMakeChange(int amount, int i, int[] sequence, LinkedHashSet<String> solutions) {
        if(i >= coins.length) return;
        if(amount <= 0) {
            if(amount == 0) solutions.add(Arrays.toString(sequence));
            return;
        }

        while(coins[i] > amount) i++;

        int[] temp = sequence.clone();
        temp[coins.length - 1 - i]++;

        printMakeChange(amount - coins[i], i, temp, solutions);
        printMakeChange(amount, i + 1, sequence.clone(), solutions);
    }

    String longestCommonSub(String a, String b) {
        return longestCommonSub(a, b, Math.min(a.length(), b.length()), "");
    }

    private String longestCommonSub(String a, String b, int len, String s) {
        if(len == 0) {
            int sCounter = 0;
            for(int bCounter = 0; bCounter < b.length() && sCounter < s.length(); bCounter++) {
                sCounter += b.charAt(bCounter) == s.charAt(sCounter) ? 1 : 0;
            }

            return sCounter == s.length() ? s : "";
        }

        String s1 = longestCommonSub(a, b, len - 1, a.charAt(len - 1) + s);
        String s2 = longestCommonSub(a, b, len - 1, s);
        return s1.length() > s2.length() ? s1 : s2;
    }
}

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