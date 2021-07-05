package part01.DynamicArray;

import DataStructureInterface.List;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    //线性表的默认容量
    private static int DEFAULT_CAPACITY = 10;
    //线性表存储元素的容器，data.length 容器的最大容量
    private E[] data;
    //线性表中元素的个数，也表示新添加元素默认在尾部添加的角标
    //size == data.length 表满
    //size == 0 表空
    private int size;

    //默认创建一个容量为DEFAULT_CAPACITY的线性表
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    //创建一个由用户指定容量capacity的线性表
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("inital capacity must >= 0");
        }
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //将一个一维数组转换成一个线性表
    public ArrayList(E[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("inital arr can not be null");
        }
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    //在线性表尾添加一个元素element
    // O(1)
    @Override
    public void add(E element) {
        add(size, element);
    }

    //在线性表中指定的index角标处添加一个元素element
    // O(n)
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add index out of bounds");
        }
        //判断线性表是否已经满了
        if (size == data.length) {
            resize(data.length * 2);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    //即实现了数组扩容的操作 也实现了数组缩容的操作 重点就在这个size的身上
    // O(n)
    private void resize(int newLength) {
        //1、先创建一个新数组
        E[] newDate = (E[]) new Object[newLength];
        //2、将原数组data中的元素复制到新数组newData中
        for (int i = 0; i < size; i++) {
            newDate[i] = data[i];
        }
        data = newDate;
    }

    //删除线性表中指定的元素element
    // O(n)
    @Override
    public void remove(E element) {
        //1.获取改元素的位置
        int index = indexOf(element);//O(n)
        //2.判断元素的存在性 如果存在则根据角标删除改元素
        if (index != -1) {
            remove(index);
        }
    }

    //删除线性表中指定角标处index的元素并返回
    // O(n)
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove index out of bounds");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        //判断是否需要缩容 不要缩容太厉害 至少保证一个最小值DEFAULT_CAPACITY
        //如果有效元素的个数是容量的1/4，且当前数组的长度是大于10的，则缩容
        if (size == data.length / 4 && data.length > DEFAULT_CAPACITY) {
            resize(data.length / 2);
        }
        return ret;
    }

    //获取线性表中指定角标处index的元素
    // O(1)
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("get index out of bounds");
        }
        return data[index];
    }

    //在线性表中修改指定角标处index的元素值为新的值element 并返回原先的值
    // O(1)
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("set index out of bounds");
        }
        E ret = data[index];
        data[index] = element;
        return ret;
    }

    //获取线性表中有效元素的个数
    // O(1)
    @Override
    public int size() {
        return size;
    }

    //获取线性表中数组的容量
    // O(1)
    public int getCapacity() {
        return data.length;
    }

    //查找线性表中指定元素element的角标 并返回
    //从左到右第一次出现的位置
    //如果元素不存在则返回-1
    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    //在线性表中判断是否包含指定的元素element
    // O(n)
    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    //判断线性表是否为空
    // O(1)
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //清空线性表
    // O(1)
    @Override
    public void clear() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    //根据比较器的规则 来对线性表中的元素进行排序【插入排序】
    // O(n^2)
    @Override
    public void sort(Comparator<E> c) {
        if (c == null) {
            throw new IllegalArgumentException("comparator can not be null");
        }
        int j = 0;
        E e = null;
        // O(n)
        for (int i = 0; i < size; i++) {
            e = data[i];
            //如果j的左边有数字，且j的左边比i的值e大，则后移
            for (j = i; j > 0 && c.compare(data[j - 1], e) > 0; j--) {
                data[j] = data[j - 1];
            }
            data[j] = e;
        }
    }

    //获取从fromIndex-toIndex区间的子线性表 [fromIndex, toIndex]
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
        ArrayList<E> subList = new ArrayList<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            subList.add(data[i]);//默认在尾部添加
        }
        return subList;
    }

    //比较两个线性表是否相等 长度相等 且 内容相等
    public boolean equals(Object obj) {
        //1.是否比较的是自己
        if (this == obj) {
            return true;
        }
        //2.判断obj是否为空
        if (obj == null) {
            return false;
        }
        //3.判断obj是否和ArrayList是同一个类型
        if (getClass() != obj.getClass()) {
            return false;
        }
        //4.具体比较内容
        ArrayList other = (ArrayList) obj;
        //5.比的长度
        if (size != other.size) {
            return false;
        }
        //6.比的就是具体数组内容
        return Arrays.equals(data, other.data);
    }

    @Override
    public String toString() {
        //ArrayList:10/30 [x,x,x,x,x]
        StringBuilder sb = new StringBuilder(String.format("ArrayList:%d/%d[", size, data.length));
        if (isEmpty()) {
            sb.append(']');//ArrayList:0/10 []
        } else {//ArrayList:5/10 [1,2,3,4,5]
            for (int i = 0; i < size; i++) {
                sb.append(data[i]);
                if (i != size - 1) {
                    sb.append(',');
                } else {
                    sb.append(']');
                }
            }
        }
        return sb.toString();
    }

    //获取ArrayList的迭代器: foreach遍历线性表  it.hasNext() it.next()
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    class ArrayListIterator implements Iterator<E> {
        private int cur = 0;

        //判断之后是否有元素
        @Override
        public boolean hasNext() {
            return cur < size;
        }

        //先把元素返回再后移
        @Override
        public E next() {
            return data[cur++];
        }
    }

}
