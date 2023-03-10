package Lab17_HuffmanCoding;

import Util.PackageFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class HuffmanTree {
    private HuffmanNode root;
    public static final char PSEUDO_EOF = 256;
    private HashMap<Integer, String> codes;

    HuffmanTree(int[] counts) {
        assert counts.length == 256;

        Queue<HuffmanNode> tree = new PriorityQueue<>();
        for (int i = 0; i < 256; i++) {
            if (counts[i] <= 0) continue;
            tree.add(new HuffmanNode(i, counts[i]));
        }

        tree.add(new HuffmanNode(PSEUDO_EOF, 1));

        while (tree.size() > 1) {
            HuffmanNode left = tree.poll();
            HuffmanNode right = tree.poll();
            HuffmanNode temp = new HuffmanNode('\0', left.weight + right.weight);

            temp.left = left;
            temp.right = right;

            tree.offer(temp);
        }

        root = tree.peek();
        treeToHashMap();

        //TreePrinter.printTree(root);
    }

    HuffmanTree(String codeFile) {
        root = new HuffmanNode('\0', 1);
        codes = new HashMap<>();

        try {
            Scanner file = new Scanner(new PackageFile(codeFile, getClass()));

            while (file.hasNextInt()) {
                int ascii = Integer.parseInt(file.nextLine());
                String code = file.nextLine();

                codes.put(ascii, code);

                HuffmanNode node = root;
                for (int i = 0; i < code.length() - 1; i++) {
                    if (code.charAt(i) == '0') {
                        if (node.left == null) {
                            node.left = new HuffmanNode('\0', 0);
                        }
                        node = node.left;
                    } else {
                        if (node.right == null) {
                            node.right = new HuffmanNode('\0', 0);
                        }
                        node = node.right;
                    }
                }

                if (code.endsWith("0")) {
                    node.left = new HuffmanNode(ascii, 0);
                } else {
                    node.right = new HuffmanNode(ascii, 0);
                }
            }

            //TreePrinter.printTree(root);
        } catch (IOException e) {
            System.out.println("Error opening " + codeFile);
        }
    }

    void write(String fileName) {
        try {
            PrintWriter file = new PrintWriter(new PackageFile(fileName, getClass()));
            write(file, root, "");
            file.close();
        } catch (IOException e) {
            System.out.println("Error opening " + fileName);
        }
    }

    private void write(PrintWriter file, HuffmanNode node, String code) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            file.println(node.ascii);
            file.println(code);
            return;
        }

        write(file, node.left, code + 0);
        write(file, node.right, code + 1);
    }

    void encode(BitOutputStream out, String fileName) {
        try {
            Scanner file = new Scanner(new PackageFile(fileName, getClass())));

            while (file.hasNextLine()) {

                for (char y : codes.get(c).toCharArray()) {
                    out.writeBit(y == '0' ? 0 : 1);
                }
            }

            for(char y : codes.get((int) PSEUDO_EOF).toCharArray()) {
                out.writeBit(y == '0' ? 0 : 1);
            }

            out.close();
        } catch (IOException e) {
            System.out.println("Error opening " + fileName);
        }
    }

    void decode(BitInputStream in, String outFile) {
        try {
            PrintWriter file = new PrintWriter(new PackageFile(outFile, getClass()));

            while (true) {
                HuffmanNode node = root;

                while (node.left != null && node.right != null) {
                    node = in.readBit() == 0 ? node.left : node.right;
                }

                char x = (char) node.ascii;

                if (x == PSEUDO_EOF) return;

                file.print(x);
            }
        } catch (IOException e) {
            System.out.println("Error opening " + outFile);
        }
    }

    private void treeToHashMap() {
        codes = new HashMap<>();

        treeToHashMap(root, "");
    }

    private void treeToHashMap(HuffmanNode node, String code) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            codes.put(node.ascii, code);
            return;
        }

        treeToHashMap(node.left, code + 0);
        treeToHashMap(node.right, code + 1);
    }
}
