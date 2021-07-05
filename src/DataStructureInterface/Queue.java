package DataStructureInterface;

public interface Queue<E> extends Iterable<E> {
    public int size();
    public boolean isEmpty();
    public void offer(E element);    //入队一个元素
    public E poll();    //出队一个元素
    public E element(); //当前队首元素
    public void clear();
}
