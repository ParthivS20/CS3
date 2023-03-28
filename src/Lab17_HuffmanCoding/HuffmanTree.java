package Lab17_HuffmanCoding;

import Util.PackageFile;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanTree {
    public static final char PSEUDO_EOF = 256;

    private HuffmanNode root;
    private HashMap<Integer, String> codes;

    HuffmanTree(int[] counts) {
        assert counts.length == 256;

        Queue<HuffmanNode> tree = new PriorityQueue<>();
        for (int i = 0; i < 256; i++) {
            if (counts[i] <= 0) continue;
            tree.offer(new HuffmanNode(i, counts[i]));
        }

        tree.offer(new HuffmanNode(PSEUDO_EOF, 1));

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
    }

    HuffmanTree(String codeFile) {
        assert codeFile.endsWith(".code");

        root = new HuffmanNode('\0', 1);
        codes = new HashMap<>();

        try {
            BufferedReader file = new BufferedReader(new FileReader(new PackageFile(codeFile, getClass())));

            String line;
            while ((line = file.readLine()) != null) {
                int ascii = Integer.parseInt(line);
                String code = file.readLine();

                codes.put(ascii, code);

                HuffmanNode node = root;
                for (int i = 0; i < code.length() - 1; i++) {
                    if (code.charAt(i) == '0') {
                        if (node.left == null) node.left = new HuffmanNode('\0', 0);
                        node = node.left;
                    } else {
                        if (node.right == null) node.right = new HuffmanNode('\0', 0);
                        node = node.right;
                    }
                }

                if (code.endsWith("0")) {
                    node.left = new HuffmanNode(ascii, 0);
                } else {
                    node.right = new HuffmanNode(ascii, 0);
                }
            }
            file.close();
        } catch (IOException e) {
            System.out.println("Error opening " + codeFile);
        }
    }

    void write(String fileName) {
        assert fileName.endsWith(".code");
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(new PackageFile(fileName, getClass())));
            write(out, root, "");
            out.close();
        } catch (IOException e) {
            System.out.println("Error opening " + fileName);
        }
    }

    private void write(BufferedWriter out, HuffmanNode node, String code) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            try {
                out.write(String.valueOf(node.ascii) + '\n');
                out.write(code + '\n');
            } catch (IOException ignored) {
            }
            return;
        }

        write(out, node.left, code + 0);
        write(out, node.right, code + 1);
    }

    void encode(String outFile, String inFile) {
        assert outFile.endsWith(".short") && inFile.endsWith(".txt");

        try {
            BufferedReader in = new BufferedReader(new FileReader(new PackageFile(inFile, getClass())));
            BitOutputStream out = new BitOutputStream(new PackageFile(outFile, getClass()));

            String line = in.readLine();
            while (line != null) {
                for (int i = 0; i < line.length(); i++) out.writeBits(codes.get((int) line.charAt(i)));
                if ((line = in.readLine()) != null) out.writeBits(codes.get((int) '\n'));
            }
            in.close();

            out.writeBits(codes.get((int) PSEUDO_EOF));
            out.close();
        } catch (IOException e) {
            System.out.println("Error opening " + inFile);
        }
    }

    void decode(String inFile, String outFile) {
        assert inFile.endsWith(".short") && outFile.endsWith(".new");

        try {
            BitInputStream in = new BitInputStream(new PackageFile(inFile, getClass()));
            BufferedWriter out = new BufferedWriter(new FileWriter(new PackageFile(outFile, getClass())));

            while (true) {
                HuffmanNode node = root;

                while (node.left != null && node.right != null) node = in.readBit() == 0 ? node.left : node.right;

                char c = (char) node.ascii;
                if (c == PSEUDO_EOF) break;
                out.write(c);
            }
            in.close();
            out.close();
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
