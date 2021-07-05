package Test;

import part01.DynamicArray.ArrayDeque;

public class TestArrayDeque {
    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        System.out.println(deque);
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        System.out.println(deque);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        System.out.println(deque);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque);

        //Deque当成Stack来用
        for (int i = 10; i <= 15; i++) {
            deque.push(i);
        }
        System.out.println(deque);
        System.out.println(deque.pop());
        System.out.println(deque);

        //Deque当成Queue来用
        for (int i = 100; i <= 105; i++) {
            deque.offer(i);
        }
        System.out.println(deque);
        System.out.println(deque.poll());
        System.out.println(deque);
    }
}
