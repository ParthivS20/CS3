package Lab27_DynamicProgramming;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClimbSteps {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new PackageFile("data/steps.dat", ClimbSteps.class));

        int T = input.nextInt();
        for(int i = 0; i < T; i++) {
            int n = input.nextInt();
            System.out.println(climb(n));
        }

        /*
        long start = System.currentTimeMillis();
        System.out.println(climb(200000000));
        System.out.println(System.currentTimeMillis() - start + "ms");

        start = System.currentTimeMillis();
        System.out.println(climb2(200000000));
        System.out.println(System.currentTimeMillis() - start + "ms");
        */
    }

    static int climb(int n) {
        if (n <= 0) return 0;

        int a = 1;
        int b = 1;
        int c = 2;

        for (int i = 3; i <= n; i++) {
            int sum = a + b + c;
            a = b;
            b = c;
            c = sum;
        }

        return c;
    }
}
