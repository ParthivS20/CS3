package Lab06_WordLadder;

import java.io.File;
import java.util.*;

public class WordLadderRunner {
    public static void main(String[] args) throws Exception {
        HashSet<String> dictionary = new HashSet<>();
        HashSet<String> removedWords = new HashSet<>();

        Scanner dictFile =  new Scanner(new File("src/Lab06_WordLadder/dictionary.txt"));
        Scanner inputFile = new Scanner(new File("src/Lab06_WordLadder/input.txt"));

        while(dictFile.hasNext()) dictionary.add(dictFile.nextLine().toLowerCase());

        while(inputFile.hasNext()) {
            String[] line = inputFile.nextLine().split(" ");
            String start = line[0];
            String end = line[1];

            if(!dictionary.contains(start) || !dictionary.contains(end)) {
                System.out.println("No Ladder found between " + start + " and " + end);
                continue;
            }

            if(start.equals(end)) {
                System.out.println("Ladder Found! >>> [" + start + "]");
                continue;
            }

            Queue<Stack<String>> queue = new LinkedList<>();
            Stack<String> stack = new Stack<>();

            queue.offer(stack);
            stack.push(start);

            dictionary.remove(start);
            removedWords.add(start);

            boolean ladderFound = false;
            while(!queue.isEmpty()) {
                Stack<String> topLadder = queue.poll();
                String topWord = topLadder.peek();

                if(topWord.equals(end)) {
                    String ladder = "";
                    while(!topLadder.isEmpty()) ladder += topLadder.pop() + ", ";

                    System.out.println("Ladder Found! >>> [" + ladder.substring(0, ladder.length() - 2) + "]");
                    ladderFound = true;

                    break;
                } else {
                    char[] word = topWord.toCharArray();
                    for(int i = 0; i < word.length; i++)
                        for(char j = 'a'; j < 'z'; j++) {
                            word[i] = j;
                            String newWord = String.valueOf(word);
                            if(dictionary.contains(newWord)) {
                                dictionary.remove(newWord);
                                removedWords.add(newWord);

                                Stack<String> newLadder = new Stack<>();
                                newLadder.addAll(topLadder);
                                newLadder.push(newWord);
                                queue.offer(newLadder);
                            }
                        }
                }
            }

            if(!ladderFound) {
                System.out.println("No Ladder found between " + start + " and " + end);
            }

            dictionary.addAll(removedWords);
            removedWords.clear();
        }
    }
}
