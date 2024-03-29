package Test05_Review;

public class Test05_ReviewTester {
    public static void main(String[] args) {
        Test05_ReviewTester.test01_reflect();
        Test05_ReviewTester.test02_save();
        Test05_ReviewTester.test03_load();
        Test05_ReviewTester.test04_build();
    }

    public static void test01_reflect() {
        Node<Integer> root = new Node<>(1);

        root.left = new Node<>(2);
        root.right = new Node<>(4);

        root.left.left = new Node<>(10);
        root.left.right = new Node<>(3);

        root.right.right = new Node<>(10);

        root.right.right.right = new Node<>(7);

        root.left.left.right = new Node<>(4);

        root.left.left.right.right = new Node<>(8);

        root.left.left.right.right.right = new Node<>(9);

        BinaryTree<Integer> tree = new BinaryTree<>(root);
        TreePrinter.printTree(tree.getRoot());
        tree.reflect();
        TreePrinter.printTree(tree.getRoot());
    }

    /**
     * you should call this method at least once before testing the load method
     */
    public static void test02_save() {
        Node<Integer> root = new Node<>(1);

        root.right = new Node<>(2);

        BinaryTree<Integer> tree = new BinaryTree<>(root); //the final tree in the doc's example table
        TreePrinter.printTree(tree.getRoot());
        tree.save("save.txt");
        /*
         * See the save.txt in project root
         * (on disk in BlueJ or Eclipse, or choose File -> Refresh in Eclipse to make it show up)
         */
    }

    public static void test03_load() {
        BinaryTree tree = new BinaryTree();

        tree.load("save.txt");

        TreePrinter.printTree(tree.getRoot());
        //should print the tree created by test02
    }

    public static void test04_build() {
        BinaryTree tree = new BinaryTree();

        TreePrinter.printTree(tree.build(4, "A"));
    }
}