package Lab04_MelodyMaker;

import java.util.LinkedList;
import java.util.Queue;

public class QueueProbsRunner {
    public static void main(String[] args) {
        QueueProbs probs = new QueueProbs();

        //evenFirst
        System.out.println(probs.evenFirst(arrayToQueue(new int[]{3, 5, 4, 17, 6, 83, 1, 84, 16, 37})));

        //numPalindrome
        System.out.println(probs.numPalindrome(arrayToQueue(new int[]{3, 8, 17, 9, 17, 8, 3})));
        System.out.println(probs.numPalindrome(arrayToQueue(new int[]{3, 10, 17, 9, 17, 8 ,3})));
        System.out.println(probs.numPalindrome(arrayToQueue(new int[]{3, 8, 17, 17, 8, 3})));
        System.out.println(probs.numPalindrome(arrayToQueue(new int[]{3, 10, 17, 17, 8 ,3})));

        //sieveOfEratosthenes
        System.out.println(probs.sieveOfEratosthenes(20));
    }

    public static Queue<Integer> arrayToQueue(int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        for (int j : arr) {
            queue.offer(j);
        }
        return queue;
    }
}
