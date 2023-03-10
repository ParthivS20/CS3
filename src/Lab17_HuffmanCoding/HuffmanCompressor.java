package Lab17_HuffmanCoding;

import Util.PackageFile;

import java.io.IOException;
import java.util.Scanner;

public class HuffmanCompressor {
    static void compress(String fileName) {
        try {
            System.out.println("Starting " + fileName);
            String fN = fileName.substring(0, fileName.length() - 4);
            int[] counts = new int[256];

            Scanner file = new Scanner(new PackageFile(fileName, HuffmanCompressor.class));
            while(file.hasNextLine()) {
                for (char x : file.nextLine().toCharArray()) counts[x]++;
                if (file.hasNextLine()) counts['\n']++;
            }
            file.close();

            HuffmanTree tree = new HuffmanTree(counts);

            System.out.println("Writing " + fN + ".code");
            tree.write(fN + ".code");

            System.out.println("Encoding to " + fN + ".short");
            tree.encode(fN + ".short", fN + ".txt");
            System.out.println("Encoding Finished");
        } catch (IOException e) {
            System.out.println("Error opening " + fileName);
        }
    }

    static void expand(String codeFile, String fileName) {
        String fN = fileName.substring(0, fileName.length() - 6);
        HuffmanTree tree = new HuffmanTree(codeFile);
        tree.decode(fileName, fN + ".new");
    }

    public static void main(String[] args) {
        String file = "happy hip hop";
        //compress("data/" + file + ".txt");
        //expand("data/" + file + ".code", "data/" + file + ".short");

        file = "short";
        //compress("data/" + file + ".txt");
        //expand("data/" + file + ".code", "data/" + file + ".short");

        file = "War and Peace";
        compress("data/" + file + ".txt");
        expand("data/" + file + ".code", "data/" + file + ".short");
    }
}
