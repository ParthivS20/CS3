package Lab26_Thievery;

import java.util.HashMap;
import java.util.Map;

public class Thievery {
    static final int[] weights = { 6, 1, 2,  5, 4, 3};
    static final int[] values  = {10, 5, 7, 12, 8, 6};
    static final int W = 10;

    public static void main(String[] args) {
        System.out.println("Maximum loot: " + thievery());
    }

    static int thievery() {
        Map<String, Integer> memo = new HashMap<>();
        return thievery(weights.length, W, memo);
    }

    private static int thievery(int index, int weightLimit, Map<String, Integer> memo) {
        if(index == 0 || weightLimit == 0) return 0;

        String curKey = index + ": " + weightLimit;
        if(memo.containsKey(curKey)) return memo.get(curKey);

        int temp = thievery(index - 1, weightLimit, memo);

        if(weights[index - 1] <= weightLimit) temp = Math.max(temp, thievery(index - 1, weightLimit - weights[index - 1], memo) + values[index - 1]);
        memo.put(curKey, temp);
        return temp;
    }
}
