package Lab04_MelodyMaker;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueProbs {
    Queue<Integer> evenFirst(Queue<Integer> nums) {
        Queue<Integer> even = new LinkedList<>();
        Queue<Integer> odd = new LinkedList<>();
        int initSize = nums.size();

        for(int i = 0; i < initSize; i++) {
            if(nums.peek() % 2 == 0) {
                even.offer(nums.poll());
            }
            else {
                odd.offer(nums.poll());
            }
        }

        while(!odd.isEmpty()) even.offer(odd.poll());

        return even;
    }

    boolean numPalindrome(Queue<Integer> nums) {
        Queue<Integer> first = new LinkedList<>();
        Stack<Integer> second = new Stack<>();

        int initSize = nums.size();
        for(int i = 0; i < initSize; i++) {
            if(initSize % 2 == 1 && i == initSize / 2) {
                nums.poll();
            }
            else if(i < initSize / 2) {
                first.offer(nums.poll());
            }
            else {
                second.push(nums.poll());
            }
        }

        while(!first.isEmpty()) if(first.poll() != second.pop()) return false;
        return true;
    }

    Stack<Integer> sieveOfEratosthenes(int n) {
        Queue<Integer> nums = new LinkedList<>();
        Stack<Integer> primes = new Stack<>();

        for(int i = 2; i <= n ; i++) nums.offer(i);

        while(!nums.isEmpty()) {
            int P = nums.poll();
            primes.push(P);

            Queue<Integer> temp = new LinkedList<>();
            while(!nums.isEmpty()) {
                int x = nums.poll();
                if(x % P != 0) temp.offer(x);
            }
            nums = temp;
        }

        return primes;
    }
}
