package Lab12_Boggle;

import Util.PackageFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class BoggleSolver {
	private final HashSet<String> dictionary;

	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
	public BoggleSolver(String dictionaryName) {
		dictionary = new HashSet<>();

		try {
			Scanner file = new Scanner(new PackageFile(dictionaryName, getClass()));
			while(file.hasNext()) {
				dictionary.add(file.next());
			}
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		HashSet<String> allValidWords = new HashSet<>();
		for(int i = 0; i < board.rows(); i++) {
			for(int j = 0; j < board.cols(); j++) {
				getAllValidWords(board, i, j, allValidWords, "", new HashSet<>());
			}
		}

		return allValidWords;
	}

	private void getAllValidWords(BoggleBoard board, int row, int col, HashSet<String> currWords, String temp, HashSet<String> checked) {
		temp += board.getLetter(row, col) == 'Q' ? "QU" : String.valueOf(board.getLetter(row, col));
		checked.add(row + "," + col);

		if(dictionary.contains(temp)) currWords.add(temp);

		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int r1 = row + i;
				int c1 = col + j;
				if((i == 0 && j == 0) || r1 < 0 || r1 >= board.rows() || c1 < 0 || c1 >= board.cols() || checked.contains(r1 + "," + c1)) continue;

				getAllValidWords(board, r1, c1, currWords, temp, checked);
			}
		}

		checked.remove(row + "," + col);
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word) {
		int len = word.length();
		if(!dictionary.contains(word) || len == 0 || len == 1 || len == 2) return 0;
		if(len == 3 || len == 4) return 1;
		if(len == 5) return 2;
		if(len == 6) return 3;
		if(len == 7) return 5;
		return 11;
	}

	public static void main(String[] args) {
		System.out.println("WORKING");

		final String PATH   = "./data/";
		BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); //should print 84

		//new BoggleGame(4, 4);
	}
}
