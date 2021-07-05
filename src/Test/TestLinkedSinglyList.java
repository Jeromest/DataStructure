package Test;

import part02.DynamicLinkedList.LinkedSinglyList;

import java.util.Comparator;
import java.util.Iterator;

public class TestLinkedSinglyList {
    public static void main(String[] args) {
        LinkedSinglyList<Integer> list = new LinkedSinglyList<>();
        System.out.println(list);
        for (int i = 1; i <= 10; i++) {
            list.add(i);;
        }
        System.out.println(list);
        list.add(0, 666);
        list.add(0, 777);
        list.add(0, 888);
        System.out.println(list);
        list.add(5, 999);
        System.out.println(list);
        int len = list.size();
        for (int i = 0; i < len; i++) {
            list.remove(0);
        }
        System.out.println(list);
        list.add(10);
        list.add(8);
        list.add(9);
        list.add(1);
        list.add(3);
        System.out.println(list);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);    //o1 -> o2  从小到大排序
            }
        });
        System.out.println(list);
        System.out.println(list.subList(0, 2));
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
