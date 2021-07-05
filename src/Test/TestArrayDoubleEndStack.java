package Test;

import part01.DynamicArray.ArrayDoubleEndStack;

public class TestArrayDoubleEndStack {
    public static void main(String[] args) {
        ArrayDoubleEndStack<Integer> stack = new ArrayDoubleEndStack<>();
        System.out.println(stack);
        for (int i = 1; i <= 6; i++) {
            stack.push(i, 0);
        }
        // 1 2 3 4 5 6
        System.out.println(stack);
        for (int i = 7; i <= 12; i++) {
            stack.push(i, 1);
        }
        //12 11 10 9 8 7
        System.out.println(stack);

        for (int i = 0; i < 4; i++) {
            System.out.println(stack.pop(0));
            System.out.println(stack.pop(1));
        }
        System.out.println(stack);

    }
}
