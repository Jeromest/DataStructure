package part01.DynamicArray;

import DataStructureInterface.Queue;

import java.util.Iterator;

public class ArrayQueue<E> implements Queue<E> {

    private ArrayList<E> list;

    public ArrayQueue() {
        this(10);
    }

    public ArrayQueue(int capacity) {
        list = new ArrayList<>(capacity);
    }

    //获取队列中有效元素的个数
    @Override
    public int size() {
        return list.size();
    }

    //判断队列是否为空
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    //入队一个元素
    @Override
    public void offer(E element) {
        list.add(element);
    }

    //出队一个元素
    @Override
    public E poll() {
        return list.remove(0);
    }

    //获取队首元素
    @Override
    public E element() {
        return list.get(0);
    }

    //清空队列
    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("ArrayQueue:%d/%d[", size(), list.getCapacity()));
        if (isEmpty()) {
            sb.append(']');
        } else {
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
