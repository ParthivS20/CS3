package Lab27_DynamicProgramming;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bottles {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new PackageFile("data/bottles1.dat", Bottles.class));

        int c = input.nextInt();
        for(int i = 0; i < c; i++) {
            int n = input.nextInt();
            int[] bottles = new int[n];
            for(int j = 0; j < n; j++) bottles[j] = input.nextInt();
            System.out.println(maxVolume(bottles));
        }
    }

    static int maxVolume(int[] bottles) {
        int n = bottles.length;
        if(n == 0) return 0;
        if(n == 1) return bottles[0];

        int a = bottles[0];
        int b = Math.max(bottles[0], bottles[1]);

        for (int i = 2; i < n; i++) {
            int max = Math.max(a + bottles[i], b);
            a = b;
            b = max;
        }

        return b;
    }
}
