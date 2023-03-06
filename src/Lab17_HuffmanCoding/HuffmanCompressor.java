package Lab17_HuffmanCoding;

public class HuffmanCompressor {
    static void compress(String fileName) {

    }

    static void expand(String codeFile, String fileName) {

    }

    public static void main(String[] args) {
        int[] counts = new int[256];

        counts['s'] = 3;
        counts['p'] = 1;
        counts['a'] = 1;
        counts['m'] = 1;

        HuffmanTree tree = new HuffmanTree(counts);
    }
}
