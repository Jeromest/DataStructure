package part01.DynamicArray;

import DataStructureInterface.Queue;

import java.util.Iterator;

//循环队列
public class ArrayLoopQueue<E> implements Queue<E> {

    /**
     * front == rear    队列为空
     * (rear + 1) % data.length == front    队列满了
     */

    //存储元素的容器
    private E[] data;
    //队首指针front
    private int front;
    //队尾指针
    private int rear;
    //有效元素的个数size
    private int size;
    //默认容量
    private static int DEFAULT_SIZE = 10;

    public ArrayLoopQueue() {
        this(DEFAULT_SIZE);
    }

    //capacity容量   存储元素的最大容量 10 = 9个可用的 + 1个不能用的
    //10 -> 11 = 10个可用的 + 1个不能用的
    public ArrayLoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        rear = 0;
        size = 0;
    }

    //获取循环队列的有效元素的个数
    @Override
    public int size() {
        return size;
    }

    //判断循环队列是否为空
    @Override
    public boolean isEmpty() {
        return size == 0 && front == rear;
    }

    //向循环队列中入队一个元素
    @Override
    public void offer(E element) {
        //判断是否需要扩容
        if ((rear + 1) % data.length == front) {
            resize(data.length * 2 - 1);
        }
        data[rear] = element;
        rear = (rear + 1) % data.length;
        size++;
    }

    //出队一个元素
    @Override
    public E poll() {
        if (isEmpty()) {
            throw new NullPointerException("queue is empty");
        }
        E ret = data[front];
        front = (front + 1) % data.length;
        size--;
        //判断是否缩容
        if (size == (data.length - 1) / 4 && data.length - 1 > DEFAULT_SIZE) {
            resize(data.length / 2 + 1);
        }
        return ret;
    }

    private void resize(int newLength) {
        E[] newData = (E[]) new Object[newLength];
        int index = 0;
        for (int i = 0; i != rear; i = (i + 1) % data.length) {
            newData[index++] = data[i];
        }
        front = 0;
        rear = index;
        data = newData;
    }

    //获取队首元素
    @Override
    public E element() {
        if (isEmpty()) {
            throw new NullPointerException("queue is empty");
        }
        return data[front];
    }

    @Override
    public void clear() {
        data = (E[]) new Object[DEFAULT_SIZE + 1];
        front = 0;
        rear = 0;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("ArrayLoopQueue:%d/%d[", size, data.length - 1));
        if (isEmpty()) {
            sb.append(']');
        } else {
            for (int i = front; i != rear; i = (i + 1) % data.length) {
                sb.append(data[i]);
                if ((i + 1) % data.length == rear) {
                    sb.append(']');
                } else {
                    sb.append(',');
                }
            }
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayLoopQueueIterator();
    }

    class ArrayLoopQueueIterator implements Iterator<E> {
        private int cur = front;    //游标    指向front

        @Override
        public boolean hasNext() {
            return cur != rear;
        }

        @Override
        public E next() {
            E ret = data[cur];
            cur = (cur + 1) % data.length;
            return ret;
        }
    }
}
