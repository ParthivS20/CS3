package Lab17_HuffmanCoding;

public class Test {
    /*
    Node root;
    char ender = (char) 300;
    char sub = '~';

    public HuffmanTree(int[] count) {
        maketree(count);
    }
    void maketree(int[] count) {
        Queue<Node> pq = new PriorityQueue<>((node1, node2) -> Integer.compare(node1.weight, node2.weight));
        for (int i = 0; i < count.length; ++i) {
            if (count[i] > 0)
                pq.add(new Node((char) i, count[i]));
        }
        pq.add(new Node(ender, 1));
        while (pq.size() > 1) {
            Node l = pq.poll(), r = pq.poll();
            Node k = new Node(sub, l.weight + r.weight);
            k.left = l;
            k.right = r;
            pq.add(k);
        }
        root = pq.poll();
        TreePrinter.printTree(root);
    }

    public HuffmanTree(String filename) {
        Queue<Node> bfs = new LinkedList<>();
        try {
            root = null;
            Scanner scan = new Scanner(new File(filename));

            while(scan.hasNext()) {
                char letter = scan.nextLine().toCharArray()[0];
                int val = Integer.parseInt(scan.nextLine());
                Node k = new Node(letter, val);
                if(root == null) root = k;
                else {
                    if(bfs.peek().left == null) bfs.peek().left = k;
                    else bfs.peek().right = k;
                }
                bfs.add(k);
            }
        } catch(Exception ex) {}
    }

    void decode(BitInputStream in, String outfile) {
        try {
            PrintWriter p = new PrintWriter(new File(outfile));
            Node cur = root;
            while(true) {
                int x = in.readBit();
                if(x == 1) cur = cur.right;
                else cur = cur.left;

                if((int)cur.c == ender) break;
                else if(cur.c != sub) {
                    p.write(cur.c);
                    cur = root;
                }
            }

        } catch(Exception ex) {}
    }

    void write(String filename) {
        try {
            Scanner output = new Scanner(new File(filename));
            Queue<Node> bfs = new LinkedList<>();
            bfs.add(root);

            while(bfs.size() > 0) {
                System.out.println(bfs.peek().c);
                System.out.println(bfs.peek().weight);
                if(bfs.peek().left != null) bfs.add(bfs.peek().left);
                if(bfs.peek().right != null) bfs.add(bfs.peek().right);
            }
        } catch(Exception ex) {}
    }

    void compress(String filename) {
        int[] res = new int[256];
        try {
            Scanner s = new Scanner(new File(filename));
            while(s.hasNext()) {
                String k = s.next();
                for(char c : k.toCharArray())
                    ++res[c];
            }

            maketree(res);
            write(filename + ".short");
        } catch(Exception ex) {}
    }

    void expand(String codeFile, String FileName) {
        decode(new BitInputStream(codeFile), FileName);
    }
*/
}
