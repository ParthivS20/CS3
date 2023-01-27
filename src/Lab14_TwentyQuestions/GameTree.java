package Lab14_TwentyQuestions;

import Util.PackageFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 */
public class GameTree {
	private Node root;
	private Node current;
	private final String fileName;

	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *          this is the name of the file we need to import the game questions
	 *          and answers from.
	 */
	public GameTree(String fileName) {
		this.fileName = "data/" + fileName;

		try {
			populate(null, null, new Scanner(new PackageFile(this.fileName, getClass())));
			current = root;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void populate(Node node, Choice branch, Scanner file) {
		Node temp = new Node(file.nextLine().trim());
		if(node == null) {
			root = temp;
		} else if(branch == Choice.Yes) {
			node.left = temp;
		} else {
			node.right = temp;
		}

		if(temp.val.endsWith("?")) {
			populate(temp, Choice.Yes,  file);
			populate(temp, Choice.No,  file);
		}
	}

	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *          The question to add where the old answer was.
	 * @param newA
	 *          The new Yes answer for the new question.
	 */
	public void add(String newQ, String newA) {
		String temp = current.val;
		current.val = newQ;
		current.left = new Node(newA);
		current.right = new Node(temp);
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer() {
		return !current.val.endsWith("?");
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.  Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent() {
		return current.val;
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo) {
		if(foundAnswer()) return;

		current = yesOrNo == Choice.Yes ? current.left : current.right;
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart() {
		current = root;
	}

	@Override
	public String toString() {
		return toString(root, 0);
	}

	private String toString(Node node, int depth) {
		String s = "";
		for(int i = 0; i < depth; i++) s += "- ";
		s += node;

		if(node.val.endsWith("?")) {
			s = toString(node.right, depth + 1) + "\n" + s + "\n" + toString(node.left, depth + 1);
		}

		return s;
	}

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
	public void saveGame() {
		try {
			PrintWriter file = new PrintWriter(new PackageFile(fileName, getClass()));
			printToFile(root, file);
			file.close();
		} catch (IOException io) {
			System.out.println("Could not create file: " + fileName);
		}
	}

	private void printToFile(Node node, PrintWriter file) {
		if(node == root) file.println(node);

		if(node.val.endsWith("?")) {
			file.println(node.left);
			printToFile(node.left, file);

			file.println(node.right);
			printToFile(node.right, file);
		}
	}
}

class Node {
	String val;
	Node left;
	Node right;

	Node(String val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return val;
	}
}