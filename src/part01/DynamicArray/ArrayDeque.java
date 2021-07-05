package part01.DynamicArray;

import DataStructureInterface.Deque;
import DataStructureInterface.Stack;

import java.util.Iterator;

//双端队列  除了有其自身的特点外  也可以当做队列和栈去使用
//没有提供在任意角标处操作元素的功能(list)
//在头的增删 在尾的增删  时间复杂度都是O(1)
public class ArrayDeque<E> implements Deque<E>, Stack<E> {

    private E[] data;
    private int front;  //队首指针    front == rear  双端队列为空
    private int rear;   //队尾指针     (rear + 1) % len == front  双端队列满了
    private int size;   //有效元素个数
    private static int DEFAULT_SIZE = 10;

    public ArrayDeque() {
        this(DEFAULT_SIZE);
    }

    public ArrayDeque(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        rear = 0;
        size = 0;
    }

    //在Deque的队首添加一个元素
    @Override
    public void addFirst(E element) {
        //先判断Deque是否是满的
        if (isExpansion()) {
            resize(data.length * 2 - 1);
        }
        front = (front - 1 + data.length) % data.length;
        data[front] = element;
        size++;
    }

    private void resize(int newLength) {
        E[] newData = (E[]) new Object[newLength];
        int index = 0;
        for (int i = front; i != rear; i = (i + 1) % data.length) {     //注意此处的i = front
            newData[index++] = data[i];
        }
        front = 0;
        rear = index;
        data = newData;
    }

    //判断Deque是否需要扩容-判断是否是满的
    private boolean isExpansion() {
        return (rear + 1) % data.length == front;
    }

    //在Deque的队尾添加一个元素
    @Override
    public void addLast(E element) {
        //先判断Deque是否是满的
        if (isExpansion()) {
            resize(data.length * 2 - 1);
        }
        data[rear] = element;
        rear = (rear + 1) % data.length;
        size++;
    }

    //在Deque队首删除一个元素
    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new NullPointerException("Deque is empty");
        }
        E ret = data[front];
        front = (front + 1) % data.length;
        size--;
        //判断是否需要缩容
        if (isShrink()) {
            resize(data.length / 2 + 1);
        }
        return ret;
    }

    private boolean isShrink() {
        return size == (data.length - 1) / 4 && data.length - 1 > DEFAULT_SIZE;
    }

    //在Deque队尾删除一个元素
    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new NullPointerException("Deque is empty");
        }
        rear = (rear - 1 + data.length) % data.length;
        E ret = data[rear];
        size--;
        //判断是否需要缩容
        if (isShrink()) {
            resize(data.length / 2 + 1);
        }
        return ret;
    }

    //获取队首元素
    @Override
    public E getFirst() {
        return data[front];
    }

    //获取队尾元素
    @Override
    public E getLast() {
        return data[(rear - 1 + data.length) % data.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && front == rear;
    }

    //入栈操作 -> addLast()  front栈底  rear栈顶
    @Override
    public void push(E element) {
        addLast(element);
    }

    //栈  出栈操作  -> removeLast() front栈底  rear栈顶
    @Override
    public E pop() {
        return removeLast();
    }

    //栈  获取栈顶元素 -> getLast() front栈底  rear栈顶
    @Override
    public E peeK() {
        return getLast();
    }

    //队列  入队 -> addLast()  front队首  rear队尾
    @Override
    public void offer(E element) {
        addLast(element);
    }

    //队列  出队 -> removeFirst()  front队首  rear队尾
    @Override
    public E poll() {
        return removeFirst();
    }

    //队列 获取队首元素  -> getFirst() front队首  rear队尾
    @Override
    public E element() {
        return getFirst();
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
        StringBuilder sb = new StringBuilder(String.format("ArrayDeque:%d/%d[", size, data.length - 1));
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
        return new ArrayDequeIterator();
    }

    //默认的迭代方向是从front -> rear
    class ArrayDequeIterator implements Iterator<E> {
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
