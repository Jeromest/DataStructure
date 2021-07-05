package Test;

import part02.DynamicLinkedList.LinkedList;

public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        System.out.println(list);
        list.add(0, 1);
        list.add(0, 2);
        list.add(0, 3);
        System.out.println(list);
        list.add(list.size(), 4);
        list.add(list.size(), 5);
        list.add(list.size(), 6);
        System.out.println(list);
        System.out.println(list.remove(3));
        System.out.println(list);
        System.out.println(list.remove(0));
        System.out.println(list.remove(list.size() - 1));
        System.out.println(list);
        for (Integer i : list) {
            System.out.println(i);
        }
        //当成栈看
        list.push(6);
        System.out.println(list);
        System.out.println(list.pop());
        System.out.println(list);
        //当成队列看
        System.out.println(list.poll());
        System.out.println(list);
        list.offer(888);
        System.out.println(list);
    }
}
