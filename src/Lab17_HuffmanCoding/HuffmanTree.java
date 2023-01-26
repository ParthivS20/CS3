package Lab17_HuffmanCoding;

public class HuffmanTree {
    private Node root;
}

class Node implements Comparable<Node> {
    int count;
    int c;
    Node left, right;

    public Node(int count, int c) {
        this.count = count;
        this.c = c;
    }

    @Override
    public int compareTo(Node o) {
        return this.count - o.count;
    }

    @Override
    public String toString() {
        if (c == -1) return count + "";
        return (char) c + "";
    }
}
