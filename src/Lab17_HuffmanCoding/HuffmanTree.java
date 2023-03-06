package Lab17_HuffmanCoding;

import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanTree {
    private HuffmanNode root;

    HuffmanTree(int[] counts) {
        assert counts.length == 256;

        Queue<HuffmanNode> tree = new PriorityQueue<>();
        for (int i = 0; i < 256; i++) {
            if (counts[i] <= 0) continue;
            tree.add(new HuffmanNode(counts[i], i));
        }

        tree.add(new HuffmanNode(counts.length, 1));

        while (tree.size() > 1) {
            HuffmanNode left = tree.poll();
            HuffmanNode right = tree.poll();
            HuffmanNode temp = new HuffmanNode(left.weight + right.weight, ' ');

            temp.left = left;
            temp.right = right;

            tree.offer(temp);
        }
    }

    HuffmanTree(String codeFile) {

    }

    void write(String fileName) {

    }

    void encode(BitOutputStream out, String fileName) {

    }

    void decode(BitInputStream in, String outFile) {

    }
}
