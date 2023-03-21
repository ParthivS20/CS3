package Lab17_HuffmanCoding;

import Util.PackageFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanCompressor {
    static void compress(String fileName) {
        try {
            System.out.println("Reading " + fileName + "...");
            String fN = fileName.substring(0, fileName.length() - 4);
            int[] counts = new int[256];

            BufferedReader file = new BufferedReader(new FileReader(new PackageFile(fileName, HuffmanCompressor.class)));
            String line = file.readLine();
            while (line != null) {
                for (int i = 0; i < line.length(); i++) counts[line.charAt(i)]++;
                if ((line = file.readLine()) != null) counts['\n']++;
            }
            file.close();

            HuffmanTree tree = new HuffmanTree(counts);

            System.out.println("Writing " + fN + ".code...");
            tree.write(fN + ".code");

            System.out.println("Writing " + fN + ".short...");
            tree.encode(fN + ".short", fN + ".txt");
        } catch (IOException e) {
            System.out.println("Error opening " + fileName);
        }
    }

    static void expand(String codeFile, String fileName) {
        HuffmanTree tree = new HuffmanTree(codeFile);
        String fN = fileName.substring(0, fileName.length() - 6);
        tree.decode(fileName, fN + ".new");
    }

    public static void main(String[] args) {
        String file = "happy hip hop";
        compress("data/" + file + ".txt");
        expand("data/" + file + ".code", "data/" + file + ".short");

        /*
        file = "short";
        compress("data/" + file + ".txt");
        expand("data/" + file + ".code", "data/" + file + ".short");

        file = "War and Peace";
        compress("data/" + file + ".txt");
        expand("data/" + file + ".code", "data/" + file + ".short");
        */
    }
}
