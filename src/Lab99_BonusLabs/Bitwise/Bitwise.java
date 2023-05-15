package Lab99_BonusLabs.Bitwise;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bitwise {
    static final char[] HEX_SYMBOLS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    static boolean q1_isEven(int n) {
        if (n < 0) return false;
        return (n & 1) != 1;
    }

    static boolean q2_isPowerOf2(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

    static boolean q3_bitIs1(int n, int i) {
        return (n >>> i & 1) == 1;
    }

    static int q4_setBitTo1(int n, int i) {
        return n | (1 << i);
    }

    static int q4_setBitTo0(int n, int i) {
        return n & ~(1 << i);
    }

    static int q5_toggleBit(int n, int i) {
        if (q3_bitIs1(n, i)) return q4_setBitTo0(n, i);
        return q4_setBitTo1(n, i);
    }

    static int q6_largestPowerOf2(int n) {
        return 0;
    }

    static String q7_32BitBinaryString(int n) {
        String binaryString = "";
        for (int i = 0; i < 32; i++) {
            binaryString += q3_bitIs1(n << i, 31) ? "X" : " ";
        }

        return binaryString;
    }

    static String q8_intToString(int n) {
        String binaryString = "";
        for (int i = 0; i < 32; i++) {
            if (i % 4 == 0) binaryString += " ";
            binaryString += n >>> (32 - i - 1) & 1;
        }

        return binaryString;
    }

    static String q9_intToHexString(int n) {
        String hexString = "0x";

        for (int i = 0; i <= 7; i++) {
            hexString += HEX_SYMBOLS[n >>> (28 - i * 4) & 0xF];
        }

        return hexString;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new PackageFile("data/numbers.txt", Bitwise.class));
        ArrayList<Integer> nums = new ArrayList<>();
        while (file.hasNextInt()) nums.add(file.nextInt());

        /*
        System.out.println(q1_isEven(12));
        System.out.println(q1_isEven(15));

        System.out.println(q2_isPowerOf2(6));
        System.out.println(q2_isPowerOf2(16));

        System.out.println(q3_bitIs1(10, 1));
        System.out.println(q3_bitIs1(10, 3));

        System.out.println(q4_setBitTo1(0, 0));

        System.out.println(q5_toggleBit(1, 0));
        */

        /*
        for(int n: nums) {
            System.out.println(q7_32BitBinaryString(n));
        }

        System.out.println(q8_intToString(42));
        System.out.println(q8_intToString(-1001));
        */

        for (int n : nums) {
            System.out.println(q9_intToHexString(n));
        }
    }
}
