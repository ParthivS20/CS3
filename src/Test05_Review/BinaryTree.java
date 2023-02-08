package Test05_Review;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

class Node<T> { //not an inner class for ease of testing
    T data;
    Node<T> left;
    Node<T> right;

    Node(T data) {
        this(data, null, null);
    }

    private Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "" + this.data;
    }
}

public class BinaryTree<T> {
    public static final String NULL = "*";
    private Node<T> overallRoot;

    /**
     * Construct an empty tree
     */
    public BinaryTree() {
    }

    /**
     * Construct a binary tree given a pre-built tree
     */
    public BinaryTree(Node<T> overallRoot) {
        this.overallRoot = overallRoot;
    }

    public Node<T> getRoot() {
        return this.overallRoot;
    }

    public void reflect() {
        reflect(overallRoot);
    }

    private void reflect(Node<T> node) {
        if (node == null) return;

        Node<T> temp = node.left;
        node.left = node.right;
        node.right = temp;

        reflect(node.left);
        reflect(node.right);
    }

    public Node<String> build(int levels, String s) {
        if (levels <= 0) return null;

        Node<String> root = new Node<>(s);
        build(levels, s, root, 1);
        return root;
    }

    private void build(int levels, String s, Node<String> node, int currLevel) {
        if (currLevel >= levels) return;

        node.left = new Node<>(s);
        node.right = new Node<>(s);

        build(levels, s, node.left, currLevel + 1);
        build(levels, s, node.right, currLevel + 1);
    }

    public void save(String fileName) {
        try {
            PrintWriter file = new PrintWriter(new PackageFile(fileName, getClass()));
            save(overallRoot, file);
            file.close();
        } catch (FileNotFoundException e) {
        }
    }

    private void save(Node<T> node, PrintWriter file) {
        if (node == null) {
            file.println(NULL);
            return;
        }

        file.println(node);
        save(node.left, file);
        save(node.right, file);
    }

    public Node<T> load(String fileName) {
        try {
            Scanner file = new Scanner(new PackageFile(fileName, getClass()));

            overallRoot = load(file);
            file.close();
        } catch (FileNotFoundException e) {
        }

        return overallRoot;
    }

    private Node<T> load(Scanner file) {
        if (!file.hasNextLine()) return null;

        String x = file.nextLine().trim();
        if (x.equals(NULL)) return null;

        Node<T> temp = new Node<>((T) (Integer) Integer.parseInt(x));

        temp.left = this.load(file);
        temp.right = this.load(file);

        return temp;
    }
}