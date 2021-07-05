package Test;

import part01.DynamicArray.ArrayList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class TestArrayList {
    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>();
//        System.out.println(list);
//        for (int i = 1; i <= 12; i++) {
//            list.add(i);
//        }
//        System.out.println(list);
//        for (int num : list) {
//            System.out.println(num);
//        }
//        System.out.println("======================");
//        Iterator<Integer> it = list.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }
//        System.out.println(list);
//        System.out.println(list.remove(0));
//        System.out.println(list.remove(0));
//        System.out.println(list.remove(0));
//        System.out.println(list.remove(0));
//        System.out.println(list.remove(0));
//        System.out.println(list.remove(0));
//        System.out.println(list);
//        System.out.println(list.remove(0));
//        System.out.println(list.remove(0));
//        System.out.println(list);
//        System.out.println(list.remove(0));
//        System.out.println(list.remove(0));
//        System.out.println(list);

        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            list.add(random.nextInt(30));   //[0, 30)
        }
        System.out.println(list);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);    //顺序排序，调换o1和o2可变为倒序排序
            }
        });
        System.out.println(list);


        ArrayList<Student> list2 = new ArrayList<>();
        list2.add(new Student("tom", 18));
        list2.add(new Student("cat", 20));
        list2.add(new Student("bob", 23));
        list2.add(new Student("david", 30));
        System.out.println(list2);
        //按name进行排序
        list2.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        System.out.println(list2);
        //按age进行排序
        list2.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.age - o2.age;
            }
        });
        System.out.println(list2);

    }
}

class Student {
    public String name;
    public int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}