package Lab17_HuffmanCoding;

import Util.PackageFile;

import java.io.IOException;
import java.util.Scanner;

public class HuffmanCompressor {
    static void compress(String fileName) {
        try {
            int[] counts = new int[256];
            Scanner file = new Scanner(new PackageFile(fileName, HuffmanCompressor.class));
            while (file.hasNext()) {
                for (char x : file.next().toCharArray()) {
                    counts[x]++;
                }
            }

            HuffmanTree tree = new HuffmanTree(counts);
            tree.write(fileName.substring(0, fileName.length() - 4) + ".code");
        } catch (IOException e) {
            System.out.println("Error opening " + fileName);
        }
    }

    static void expand(String codeFile, String fileName) {

    }

    public static void main(String[] args) {
        int[] counts = new int[256];

        counts['a'] = 3;
        counts['b'] = 3;
        counts['c'] = 1;
        counts['x'] = 1;
        counts['y'] = 2;

        HuffmanTree tree = new HuffmanTree(counts);
        tree.write("output/test.code");

        HuffmanTree tree1 = new HuffmanTree("output/test.code");
    }
}
