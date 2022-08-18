package Lab02_MyStack;

import java.util.Stack;

public class StackProbs {
    Stack<Integer> doubleUp(Stack<Integer> nums) {
        Stack<Integer> temp = new Stack<>();
        while(nums.size() > 0) {
            Integer x = nums.pop();
            temp.push(x);
            temp.push(x);
        }

        int size = temp.size();
        for(int i = 0; i < size; i++) {
            nums.push(temp.pop());
        }

        return nums;
    }

    Stack<Integer> posAndNeg(Stack<Integer> nums) {
        Stack<Integer> pos = new Stack<>();
        Stack<Integer> neg = new Stack<>();
        int size = nums.size();
        for(int i = 0; i < size; i++) {
            int x = nums.pop();
            if(x < 0) {
                neg.push(x);
            }
            else {
                pos.push(x);
            }
        }

        while(pos.size() > 0) {
            neg.push(pos.pop());
        }

        return neg;
    }

    Stack<Integer> shiftByN(Stack<Integer> nums, int n) {
        Stack<Integer> firstN = new Stack<>();
        Stack<Integer> temp = new Stack<>();
        int size = nums.size();
        for(int i = 0; i < size - n; i++) {
            temp.push(nums.pop());
        }
        for(int i = 0; i < n; i++) {
            firstN.push(nums.pop());
        }

        int size2 = temp.size();
        for(int i = 0; i < size2; i++) {
            nums.push(temp.pop());
        }
        for(int i = 0; i < n; i++) {
            nums.push(firstN.pop());
        }

        return nums;
    }
}
