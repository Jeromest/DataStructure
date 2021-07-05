package Test;

import part01.DynamicArray.ArrayLoopQueue;

public class TestArrayLoopQueue {
    public static void main(String[] args) {
        ArrayLoopQueue<Integer> queue = new ArrayLoopQueue<>();
        for (int i = 1; i <= 12; i++) {
            queue.offer(i);
        }
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);
        for (int num : queue) {
            System.out.print(num + " ");
        }
    }
}
