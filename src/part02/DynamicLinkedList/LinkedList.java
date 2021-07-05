package part02.DynamicLinkedList;

import DataStructureInterface.Deque;
import DataStructureInterface.List;
import DataStructureInterface.Stack;

import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<E> implements List<E>, Deque<E>, Stack<E> {

    //定义节点
    private class Node {
        E data;
        Node pre;   //指向直接前驱的指针
        Node next;  //指向直接后继的指针

        public Node() {
            this(null, null, null);
        }

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node pre, Node next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node head;  //头指针
    private Node tail;  //尾指针
    private int size;   //有效元素个数

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public LinkedList(E[] arr) {
        for (E e : arr) {
            add(e);
        }
    }

    //默认向表尾添加元素
    @Override
    public void add(E element) {
        add(size, element);
    }

    //在指定角标处index添加元素element

    /**
     * 在表头插入一个元素
     * 1.把head的上一跳 给 新节点的 上一跳
     * 2.新节点的下一跳 指向 head
     * 3.head的上一跳 指向 新节点
     * 4.更新head 让head指向新节点
     * 5.更新tail 让tail的下一跳重新指向head
     * <p>
     * <p>
     * 在表尾插入一个元素
     * 1.把tail的下一跳 给 新节点的 下一跳
     * 2.tail的下一跳 指向 新节点
     * 3.新节点的 上一跳 指向tail
     * 4.tail重新指向新节点
     * 5.head的上一跳重新指向tail
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("add index out of bounds");
        }
        Node node = new Node(element);
        if (isEmpty()) {
            head = node;
            tail = node;
            tail.next = head;
            head.pre = tail;
        } else if (index == 0) {
            //在表头添加一个元素
            node.pre = head.pre;
            node.next = head;
            head.pre = node;
            head = node;
            tail.next = head;
        } else if (index == size) {
            //在尾部添加一个元素
            node.next = tail.next;
            tail.next = node;
            node.pre = tail;
            tail = node;
            head.pre = tail;
        } else {
            //在中间添加一个元素
            Node p, q;
            if (index <= size / 2) {
                //从头部开始插入
                p = head;
                for (int i = 0; i < index - 1; i++) {
                    p = p.next;
                }
                q = p.next;
                p.next = node;
                node.pre = p;
                q.pre = node;
                node.next = q;
            } else {
                //从尾部开始插入
                p = tail;
                for (int i = size - 1; i > index; i--) {
                    p = p.pre;
                }
                q = p.pre;
                q.next = node;
                node.pre = q;
                p.pre = node;
                node.next = p;
            }
        }
        size++;
    }

    @Override
    public void remove(E element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 删除头结点
     * 1.node为head.next 最终node是新的头结点
     * 2.head的下一跳置空
     * 3.head的上一跳 给 node的上一跳 head的上一跳置空
     * 4.head重新指向node
     * 5.tail的下一跳重新指向head
     * <p>
     * 删除尾结点
     * 1.node为tail.pre 最终node是新的尾结点
     * 2.tail的上一跳置空
     * 3.tail的下一跳 给 node的下一跳 tail的下一跳置空
     * 4.tail重新指向node
     * 5.head的上一跳重新指向tail
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("remove index out of bounds");
        }
        E ret = null;
        Node node;
        if (size == 1) {
            ret = head.data;
            head = null;
            tail = null;
        } else if (index == 0) {
            //从头部删除元素
            ret = head.data;
            node = head.next;
            head.next = null;
            node.pre = head.pre;
            head.pre = null;
            head = node;
            tail.next = head;
        } else if (index == size - 1) {
            //从尾部删除元素
            ret = tail.data;
            node = tail.pre;
            tail.pre = null;
            node.next = tail.next;
            tail.next = null;
            tail = node;
            head.pre = tail;
        } else {
            //从中间删除元素
            Node p, q, r;
            if (index <= size / 2) {
                p = head;
                for (int i = 0; i < index - 1; i++) {
                    p = p.next;
                }
                q = p.next;
                ret = q.data;
                r = q.next;
                p.next = r;
                r.pre = p;
                q.next = null;
                q.pre = null;
            } else {
                p = tail;
                for (int i = size - 1; i > index + 1; i--) {
                    p = p.pre;
                }
                q = p.pre;
                ret = q.data;
                r = q.pre;
                r.next = p;
                p.pre = r;
                q.next = null;
                q.pre = null;
            }
        }
        size--;
        return ret;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("get index out of bounds");
        }
        //1.获取头
        if (index == 0) {
            return head.data;
        } else if (index == size - 1) {
            //2.获取尾
            return tail.data;
        } else {
            //3.获取中间
            Node p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.data;
        }
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("set index out of bounds");
        }
        E ret = null;
        if (index == 0) {
            //1.修改头
            ret = head.data;
            head.data = element;
        } else if (index == size - 1) {
            //2.修改尾
            ret = tail.data;
            tail.data = element;
        } else {
            //3.修改中间某一个元素
            Node p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            ret = p.data;
            p.data = element;
        }
        return ret;
    }

    @Override
    public void addFirst(E element) {
        add(0, element);
    }

    @Override
    public void addLast(E element) {
        add(size, element);
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public E getFirst() {
        return get(0);
    }

    @Override
    public E getLast() {
        return get(size - 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E element) {
        Node p = head;
        int index = 0;
        while (!p.data.equals(element)) {
            p = p.next;
            index++;
            if (p == head) {    //change code
                return -1;
            }
        }
        return index;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && head == null && tail == null;
    }

    @Override
    public void push(E element) {
        addLast(element);
    }

    @Override
    public E pop() {
        return removeLast();
    }

    @Override
    public E peeK() {
        return getLast();
    }

    @Override
    public void offer(E element) {
        addLast(element);
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void sort(Comparator<E> c) {
        if (c == null) {
            throw new IllegalArgumentException("comparator can not be null");
        }
        //改进代码   选择排序的思想
        if (size == 0 || size == 1) {
            return;
        }
        Node nodeA = head;
        Node nodeB = nodeA.next;
        while (true) {
            while (true) {
                if (c.compare(nodeA.data, nodeB.data) > 0) {
                    swap(nodeA, nodeB);
                }
                if (nodeB == tail) {
                    break;
                }
                nodeB = nodeB.next;
            }
            if (nodeA.next == tail) {
                break;
            }
            nodeA = nodeA.next;
            nodeB = nodeA.next;
        }
    }

    private void swap(Node nodeA, Node nodeB) {
        E temp = nodeA.data;
        nodeA.data = nodeB.data;
        nodeB.data = temp;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex must >= 0");
        }
        if (toIndex >= size) {
            throw new IndexOutOfBoundsException("toIndex must < size");
        }
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex must <= toIndex");
        }
        LinkedList<E> subList = new LinkedList<>();
        //改进
        Node nodeA = head;
        for (int i = 0; i < fromIndex; i++) {
            nodeA = nodeA.next;
        }
        Node nodeB = head;
        for (int i = 0; i < toIndex; i++) {
            nodeB = nodeB.next;
        }
        Node p = nodeA;
        while (true) {
            subList.add(p.data);
            if (p == nodeB) {
                break;
            }
            p = p.next;
        }
        return subList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("LinkedList:%d [", size));
        if (isEmpty()) {
            sb.append(']');
        } else {
            Node p = head;
            while (true) {
                sb.append(p.data);
                if (p == tail) {
                    sb.append(']');
                    break;
                }
                sb.append(',');
                p = p.next;
            }
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    class LinkedListIterator implements Iterator<E> {
        private Node cur = head;
        private boolean flag = true;    //表示可以继续(还在一圈内)
        @Override
        public boolean hasNext() {
            if (isEmpty()) {
                return false;
            }
            return flag;
        }

        @Override
        public E next() {
            E ret = cur.data;
            cur = cur.next;
            if (cur == head) {
                flag = false;   //表示不可以继续(跑完了一圈)
            }
            return ret;
        }
    }
}
