package Test;

import part01.DynamicArray.ArrayStack;

public class TestStack {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        System.out.println(stack);
        for (int i = 1; i <= 12; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        for (int i = 0; i < 7; i++) {
            System.out.println(stack.pop());
        }
        System.out.println(stack);
        for (int num : stack) {
            System.out.println(num);
        }
    }
}
