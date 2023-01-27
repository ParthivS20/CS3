package Lab06_WordLadder;

import Utils.ConsoleColors;
import Utils.PackageFile;

import java.util.*;

public class WordLadder {
    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();

        Scanner dictFile =  new Scanner(new PackageFile("data/dictionary.txt", WordLadder.class));
        Scanner inputFile = new Scanner(new PackageFile("data/input.txt", WordLadder.class));
        Set<String> dictionary = new HashSet<>();
        Set<String> removedWords = new HashSet<>();

        while(dictFile.hasNext()) {
            dictionary.add(dictFile.nextLine().toLowerCase());
        }

        while(inputFile.hasNext()) {
            String[] line = inputFile.nextLine().split(" ");
            String start = line[0];
            String end = line[1];

            if(!dictionary.contains(start) || !dictionary.contains(end) || start.length() != end.length()) {
                System.out.println("No ladder found between " + ConsoleColors.GREEN.apply(start) + " and " + ConsoleColors.CYAN.apply(end));
                continue;
            }

            if(start.equals(end)) {
                System.out.println("Found a ladder! >>> [" + ConsoleColors.GREEN.apply(start) + "]");
                continue;
            }

            Queue<Stack<String>> queue = new LinkedList<>();
            Stack<String> stack = new Stack<>();

            stack.push(start);
            queue.offer(stack);

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
                            text = ConsoleColors.GREEN.apply(text);
                        }
                        else {
                            char[] word1 = word.toCharArray();
                            char[] word2 = topLadder.peek().toCharArray();

                            for(int i = 0; i < word1.length; i++) {
                                if(word1[i] != word2[i]) {
                                    if(ladder.isBlank()) {
                                        text = ConsoleColors.CYAN.apply(text.substring(0, i)) + ConsoleColors.CYAN_BOLD_BRIGHT.apply(word1[i] + "") + ConsoleColors.CYAN.apply(text.substring(i + 1));
                                    }
                                    else {
                                        text = text.substring(0, i) + ConsoleColors.RED_BOLD.apply(word1[i] + "") + text.substring(i + 1);
                                    }
                                    break;
                                }
                            }
                        }

                        ladder = text + ", " + ladder;
                    }

                    System.out.println("Found a ladder! >>> [" + ladder.substring(0, ladder.length() - 2) + "]");
                    ladderFound = true;
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
                System.out.println("No ladder found between " + ConsoleColors.GREEN.apply(start) + " and " + ConsoleColors.CYAN.apply(end));
            }

            dictionary.addAll(removedWords);
            removedWords.clear();
        }

        System.out.println("Program Runtime: " + ConsoleColors.BLUE.apply((System.nanoTime() - startTime) / 1000000 + "ms"));
    }
}
