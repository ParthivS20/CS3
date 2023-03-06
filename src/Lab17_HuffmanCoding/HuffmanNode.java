package Lab17_HuffmanCoding;

public class HuffmanNode implements Comparable<HuffmanNode> {
    int weight;
    int ascii;
    HuffmanNode left, right;

    public HuffmanNode(int weight, int ascii) {
        this.weight = weight;
        this.ascii = ascii;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        if (ascii == -1) return weight + "";
        return (char) ascii + "";
    }
}
