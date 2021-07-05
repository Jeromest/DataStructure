package DataStructureInterface;

public interface Stack<E> extends Iterable<E> {
    public int size();
    public boolean isEmpty();
    public void push(E element);
    public E pop();
    public E peeK();
    public void clear();
}
