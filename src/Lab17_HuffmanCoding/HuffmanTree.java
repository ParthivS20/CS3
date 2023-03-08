package Lab17_HuffmanCoding;

import Util.PackageFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class HuffmanTree {
    private HuffmanNode root;
    public static final char PSEUDO_EOF = 256;
    private HashMap<Character, String> codes;
    private HashMap<String, Character> characters;

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

        treeToHashMap();

        root = tree.peek();
        //TreePrinter.printTree(root);
    }

    HuffmanTree(String codeFile) {
        root = new HuffmanNode('\0', 1);
        codes = new HashMap<>();

        try {
            Scanner file = new Scanner(new PackageFile(codeFile, getClass()));

            while (file.hasNextInt()) {
                int ascii = file.nextInt();
                file.nextLine();
                String code = file.nextLine();

                codes.put((char) ascii, code);
                characters.put(code, (char) ascii);

                HuffmanNode node = root;
                for (int i = 0; i < code.length() - 1; i++) {
                    if (code.substring(i, i + 1).equals("0")) {
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

                if (code.substring(code.length() - 1).equals("0")) {
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
            Scanner file = new Scanner(new PackageFile(fileName, getClass()));

            while (file.hasNext()) {
                for (char x : file.next().toCharArray()) {
                    for (char y : codes.get(x).toCharArray()) {
                        out.writeBit(y == '0' ? 0 : 1);
                    }
                }
            }

            for (char y : codes.get(PSEUDO_EOF).toCharArray()) {
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

    /*
    void decode(BitInputStream in, String outFile) {
        try {
            PrintWriter file = new PrintWriter(new PackageFile(outFile, getClass()));

            while(true) {
                HuffmanNode node = root;

                String code = "";
                do {
                    code += in.readBit();
                } while(characters.get(code) == null);

                char x = characters.get(code);

                if(x == PSEUDO_EOF) return;

                file.print(x);
            }
        } catch (IOException e) {
            System.out.println("Error opening " + outFile);
        }
    }*/

    private void treeToHashMap() {
        codes = new HashMap<>();
        characters = new HashMap<>();

        treeToHashMap(root, "");
    }

    private void treeToHashMap(HuffmanNode node, String code) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            codes.put((char) node.ascii, code);
            characters.put(code, (char) node.ascii);
            return;
        }

        treeToHashMap(node.left, code + 0);
        treeToHashMap(node.right, code + 1);
    }
}
