package Lab17_HuffmanCoding;

import Util.PackageFile;

import java.io.IOException;
import java.util.Scanner;

public class HuffmanCompressor {
    static void compress(String fileName) {
        try {
            int[] counts = new int[256];

            Scanner file = new Scanner(new PackageFile(fileName, HuffmanCompressor.class));
            while(file.hasNextLine()) {
                for (char x : file.nextLine().toCharArray()) {
                    counts[x]++;
                }
                counts['\n']++;
            }
            if(counts['\n'] > 0) counts['\n']--;

            HuffmanTree tree = new HuffmanTree(counts);
            tree.write(fileName.substring(0, fileName.length() - 4) + ".code");
            tree.encode(new BitOutputStream("data/happy hip hop.short"), "data/happy hip hop.txt");
        } catch (IOException e) {
            System.out.println("Error opening " + fileName);
        }
    }

    static void expand(String codeFile, String fileName) {

    }

    public static void main(String[] args) {
        compress("data/happy hip hop.txt");
    }
}
