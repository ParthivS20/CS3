package Lab11_ForestFire;

import java.util.Arrays;
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
	if(len == 0) return sum <= limit ? sum : -1;
	return Math.max(getMax(nums, limit,sum + nums.get(len - 1), len - 1), getMax(nums, limit, sum, len - 1));
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
        printMakeChange(amount, 0);
    }

    private void printMakeChange(int amount, int counter) {
		System.out.println(" P  N  D  Q");
		System.out.println("------------");
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

static void makeChangePrint(int amt, int index, int[] a) {
		if(index >= 4) return;
		if(amt <= 0) {
			if(amt == 0) System.out.println(Arrays.toString(a));
			return;
		}
		int [] d = {25, 10, 5, 1};
		while(d[index] > amt) ++index;
		int[] c = a.clone(); ++c[3 - index];
		makeChangePrint(amt - d[index], index, c);
		makeChangePrint(amt, index + 1, new int[]{a[0], a[1], a[2], a[3]});
	}
	static String longestCommonSub(String a, String b, int size, String curString) {
		if(size == 0) {
			int ind = 0;
			for(int i = 0; i < b.length() && ind < curString.length(); ++i) 
				ind += (b.charAt(i) == curString.charAt(ind) ? 1 : 0);
			return (ind == curString.length() ? curString : "");
		}
		String ans = longestCommonSub(a, b, size-1, a.charAt(size-1) + curString), op2 = longestCommonSub(a, b, size-1, curString);
		return (op2.length() > ans.length() ? op2 : ans); 
	}
