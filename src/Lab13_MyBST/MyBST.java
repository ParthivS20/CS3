package Lab13_MyBST;

class MyBST<T extends Comparable<T>> {
    private Node root;

    int size() {
        return size(root);
    }

    private int size(Node root) {
        return root == null ? 0 : 1 + size(root.left) + size(root.right);
    }

    void insert(T val) {
        if (root == null) {
            root = new Node(val);
        } else {
            insert(val, root);
        }
    }

    private void insert(T val, Node root) {
        if(val.equals(root.val)) return;

        if(val.compareTo(root.val) < 0) {
            if(root.left == null) {
                root.left = new Node(val);
            } else {
                insert(val, root.left);
            }
        } else {
            if(root.right == null) {
                root.right = new Node(val);
            } else {
                insert(val, root.right);
            }
        }
    }

    boolean contains(T val) {
        return contains(val, root);
    }

    private boolean contains(T val, Node node) {
        if(node == null) return false;

        if(val.equals(node.val)) return true;

        return val.compareTo(node.val) < 0 ? contains(val, node.left) : contains(val, node.right);
    }

    T getMax() {
        return getMax(root);
    }

    private T getMax(Node root) {
        while(root.right != null) root = root.right;

        return root.val;
    }

    T getMin() {
        return getMin(root);
    }

    private T getMin(Node root) {
        while(root.left != null) root = root.left;

        return root.val;
    }

    void delete(T val) {
        delete(val, root);
    }

    private Node delete(T val, Node root) {
        if(root == null) return null;

        if(val.compareTo(root.val) < 0) {
            root.left = delete(val, root.left);
            return root;
        }

        if(val.compareTo(root.val) > 0) {
            root.right = delete(val, root.right);
            return root;
        }

        if(root.left == null || root.right == null) {
            return root.right == null ? root.left : root.right;
        }

        root.val = getMax(root.left);
        root.left = delete(root.val, root.left);
        return root;
    }

    void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node root) {
        if(root == null) return;

        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    void print() {
        print(1, root);
    }

    private void print(int depth, Node root) {
        if(root == null) return;

        print(depth + 1, root.right);

        for(int i = 1; i < depth; i++) {
            System.out.print("\t");
        }
        System.out.println(root);

        print(depth + 1, root.left);
    }

    private class Node {
        T val;
        Node left, right;

        Node(T val) {
            this.val = val;
        }

        @Override
        public
        String toString() {
            return String.valueOf(this.val);
        }
    }
}
