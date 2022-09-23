package Lab06_WordLadder;

import Utils.ConsoleColors;

import java.io.File;
import java.util.*;

public class WordLadderRunner {
    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();

        Set<String> dictionary = new HashSet<>();
        Set<String> removedWords = new HashSet<>();

        Scanner dictFile =  new Scanner(new File("src/Lab06_WordLadder/dictionary.txt"));
        Scanner inputFile = new Scanner(new File("src/Lab06_WordLadder/input.txt"));

        while(dictFile.hasNext()) {
            dictionary.add(dictFile.nextLine().toLowerCase());
        }

        while(inputFile.hasNext()) {
            String[] line = inputFile.nextLine().split(" ");
            String start = line[0];
            String end = line[1];

            if(!dictionary.contains(start) || !dictionary.contains(end)) {
                System.out.println("No Ladder found between " + color(start, ConsoleColors.GREEN) + " and " + color(end, ConsoleColors.CYAN));
                continue;
            }

            if(start.equals(end)) {
                System.out.println("Ladder Found! >>> [" + color(start, ConsoleColors.GREEN) + "]");
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
                    while(!topLadder.isEmpty()) {
                        String word = topLadder.pop();
                        String text = word;
                        if(topLadder.isEmpty()) {
                            text = color(text, ConsoleColors.GREEN);
                        }
                        else {
                            int changedIndex = -1;
                            char[] word1 = word.toCharArray();
                            char[] word2 = topLadder.peek().toCharArray();
                            for(int i = 0; i < word1.length; i++) {
                                if(word1[i] != word2[i]) {
                                    changedIndex = i;
                                    break;
                                }
                            }

                            if(ladder.isBlank()) {
                                text = color(text.substring(0, changedIndex), ConsoleColors.CYAN) + color(word1[changedIndex] + "",  ConsoleColors.CYAN_BOLD_BRIGHT) + color(text.substring(changedIndex + 1), ConsoleColors.CYAN);
                            }
                            else {
                                text = text.substring(0, changedIndex) + color(word1[changedIndex] + "",  ConsoleColors.RED_BOLD) + text.substring(changedIndex + 1);
                            }
                        }
                        ladder = text + ", " + ladder;
                    }

                    System.out.println("Ladder Found! >>> [" + ladder.substring(0, ladder.length() - 2) + "]");
                    ladderFound = true;
                    break;
                }
                else {
                    char[] word = topWord.toCharArray();
                    for(int i = 0; i < word.length; i++) {
                        char originalLetter = word[i];
                        for (char j = 'a'; j <= 'z'; j++) {
                            word[i] = j;
                            String newWord = new String(word);
                            if (dictionary.contains(newWord)) {
                                dictionary.remove(newWord);
                                removedWords.add(newWord);

                                Stack<String> newLadder = new Stack<>();
                                newLadder.addAll(topLadder);
                                newLadder.push(newWord);
                                queue.offer(newLadder);
                            }
                        }
                        word[i] = originalLetter;
                    }
                }
            }

            if(!ladderFound) {
                System.out.println("No Ladder found between " + color(start, ConsoleColors.GREEN) + " and " + color(end, ConsoleColors.CYAN));
            }

            dictionary.addAll(removedWords);
            removedWords.clear();
        }

        System.out.println("Program Runtime: " + color((System.nanoTime() - startTime) / 1000000 + "ms", ConsoleColors.BLUE));
    }

    public static String color(String text, String color) {
        return color + text + ConsoleColors.RESET;
    }
}
