package part02.DynamicLinkedList;

import DataStructureInterface.List;

import java.util.Comparator;
import java.util.Iterator;

//单向循环链表
public class LinkedSinglyCircularList<E> implements List<E> {

    //定义出一个节点  内部类
    private class Node {
        E data; //数据域  用来存储数据的
        Node next;  //指针域  用来存储下一个节点对象的地址

        public Node() {
            this(null, null);
        }

        public Node(E data) {
            this(data, null);
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node head;  //是链表当中的头指针  指向第一个节点对象
    private Node tail;  //是链表当中的尾指针  指向最后一个节点对象
    private int size;   //是链表当中有效元素的个数

    public LinkedSinglyCircularList() {
        head = null;
        tail = null;
        size = 0;
    }

    public LinkedSinglyCircularList(E[] arr) {
        for (E e : arr) {
            add(e);
        }
    }

    //默认在链表的尾部添加元素
    @Override
    public void add(E element) {
        add(size, element);
    }

    //在链表当中指定角标index处添加一个元素element
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("add index out of bounds");
        }
        //创建一个新的节点对象
        Node node = new Node(element);
        //1.链表为空
        if (isEmpty()) {
            head = node;
            tail = node;
            tail.next = head;   //new code
        } else if (index == 0) {
            //2.在表头添加
            node.next = head;
            head = node;
            tail.next = head;   //new code
        } else if (index == size) {
            //3.在表尾添加
            node.next = tail.next;   //new code
            tail.next = node;
            tail = node;
        } else {
            //4.在表中间添加
            Node p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            node.next = p.next;
            p.next = node;
        }
        size++;
    }

    //删除链表中指定的元素element
    @Override
    public void remove(E element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        }
    }

    //删除链表中指定角标处index的元素
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("remove index out of bounds");
        }
        E ret = null;
        //1.当链表只剩一个元素
        if (size == 1) {
            ret = head.data;
            head = null;
            tail = null;
        } else if (index == 0) {
            //2.删除表头
            Node del = head;
            ret = del.data;
            head = del.next;
            del.next = null;
            tail.next = head;   //new code
        } else if (index == size - 1) {
            //3.删除表尾
            Node p = head;
            while (p.next != tail) {
                p = p.next;
            }
            ret = tail.data;
            p.next = tail.next; //change code
            tail = p;
        } else {
            //4.删除中间某一个元素
            Node p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            Node del = p.next;
            ret = del.data;
            p.next = del.next;
            del.next = null;
        }
        size--;
        return ret;
    }

    //获取链表中指定角标处的元素
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

    //修改链表中指定角标处index的元素为element
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
    public int size() {
        return size;
    }

    //查找元素在链表中第一次出现的位置
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

    //在链表中判断是否包含元素element
    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && head == null && tail == null;
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
        /*
        int j = 0;
        E e = null;
        for (int i = 0; i < size; i++) {
            e = get(i);
            for (j = i; j > 0 && c.compare(get(j - 1), e) > 0; j--) {
                set(j, get(j - 1));
            }
            set(j, e);
        }
         */
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
        LinkedSinglyList<E> subList = new LinkedSinglyList<>();
        /*
        for (int i = fromIndex; i <= toIndex; i++) {
            subList.add(get(i));//默认在尾部添加
        }
         */
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
        StringBuilder sb = new StringBuilder(String.format("LinkedSinglyList:%d [", size));
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
        return new LinkedSinglyCircularListIterator();
    }

    class LinkedSinglyCircularListIterator implements Iterator<E> {
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

    //约瑟夫环问题
    public void josephusLoop() {
        if (size <= 2) {
            return;
        }

        Node p = head;
        while (size != 2) {
            p = p.next;
            //删除节点
            Node del = p.next;
            if (del == head) {
                head = head.next;
            } else if (del == tail) {
                tail = p;
            }
            p.next = del.next;
            del.next = null;
            size--;
            p = p.next;

        }

    }
}
