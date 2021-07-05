package Test;

import part01.DynamicArray.ArrayQueue;

public class TestArrayQueue {
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 1; i <= 12; i++) {
            queue.offer(i);
        }
        System.out.println(queue);
        for (int i = 0; i < 8; i++) {
            System.out.println(queue.poll());
        }
        System.out.println(queue);
        System.out.println(queue.element());
        for (int num : queue) {
            System.out.print(num + " ");
        }
    }
}
