package part01.DynamicArray;

import DataStructureInterface.Stack;

import java.util.Iterator;


public class ArrayStack<E> implements Stack<E> {

    //栈内部其实就是由一个线性表来实现
    private ArrayList<E> list;

    //创建一个默认容量的栈（默认容量的线性表）
    public ArrayStack() {
        list = new ArrayList<E>();
    }

    //创建一个指定容量的栈（指定容量的线性表）
    public ArrayStack(int capacity) {
        list = new ArrayList<>(capacity);
    }

    //获取栈中有效元素的个数
    @Override
    public int size() {
        return list.size();
    }

    //判断栈是否为空
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    //入栈一个元素（在线性表的表尾添加一个元素）
    @Override
    public void push(E element) {
        list.add(element);
    }

    //弹栈一个元素并返回（在线性表的表尾删除一个元素）
    @Override
    public E pop() {
        return list.remove(list.size() - 1);
    }

    //查看当前栈顶元素（不删除）
    @Override
    public E peeK() {
        return list.get(list.size() - 1);
    }

    //清空栈（清空线性表）
    @Override
    public void clear() {
        list.clear();
    }

    //迭代器
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("ArrayStack:%d/%d[", size(), list.getCapacity()));
        if (isEmpty()) {
            sb.append(']');//ArrayStack:0/10 []
        } else {//ArrayStack:5/10 [1,2,3,4,5]
            for (int i = 0; i < size(); i++) {
                sb.append(list.get(i));
                if (i != size() - 1) {
                    sb.append(',');
                } else {
                    sb.append(']');
                }
            }
        }
        return sb.toString();
    }
}
