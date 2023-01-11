package Lab13_MyBST;

public class MyBST<T extends Comparable<T>> {
    private Node root;

    int size() {
        return size(root);
    }

    private int size(Node node) {
        if(node == null) return 0;

        return 1 + size(node.left) + size(node.right);
    }

    int height() {
        if(root == null) return 1;
        return height(root);
    }

    private int height(Node node) {
        if(node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    void insert(T val) {
        if(root == null) root = new Node(val);
        else insert(val, root);
    }

    private void insert(T val, Node node) {
        if(val.equals(node.val)) return;

        if(val.compareTo(node.val) < 0) {
            if(node.left == null) node.left = new Node(val);
            else insert(val, node.left);
        }
        else {
            if(node.right == null) node.right = new Node(val);
            else insert(val, node.right);
        }
    }

    void delete(T val) {
        delete(val, root);
    }

    private void delete(T val, Node node) {
        if(node == null) return;
    }

    boolean contains(T val) {
        return contains(val, root);
    }

    private boolean contains(T val, Node node) {
        if(node == null) return false;

        if(val.equals(node.val)) return true;

        return val.compareTo(node.val) < 0 ? contains(val, node.left) : contains(val, node.right);
    }

    T getMin() {
        Node node = root;
        while(node.left != null) node = node.left;

        return node.val;
    }

    T getMax() {
        Node node = root;
        while(node.right != null) node = node.right;

        return node.val;
    }


    void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if(node == null) return;

        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    void print() {
        print(root);
    }

    private void print(Node node) {

    }

    private class Node {
        T val;
        Node left, right;

        public Node(T val) {
            this.val = val;
            left = null;
            right = null;
        }

        @Override
        public
        String toString() {
            return String.valueOf(this.val); }
    }
}
