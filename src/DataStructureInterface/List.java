package DataStructureInterface;

import java.util.Comparator;

//线性表的接口定义 继承了迭代接口Iterable
public interface List<E> extends Iterable<E> {
    public void add(E element);
    public void add(int index, E element);
    public void remove(E element);
    public E remove(int index);
    public E get(int index);
    public E set(int index, E element);
    public int size();
    public int indexOf(E element);
    public boolean contains(E element);
    public boolean isEmpty();
    public void clear();
    public void sort(Comparator<E> c);
    public List<E> subList(int fromIndex, int toIndex);

}
