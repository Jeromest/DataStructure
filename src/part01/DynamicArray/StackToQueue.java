package part01.DynamicArray;


//用栈实现队列
public class StackToQueue {
    public static void main(String[] args) {
        QueueImplByStack<Integer> queue = new QueueImplByStack<>();
        for (int i = 1; i <= 10; i++) {
            queue.offer(i);
        }
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.element());
    }
}

//用栈实现的那个队列
class QueueImplByStack<E> {
    private ArrayStack<E> stackA;
    private ArrayStack<E> stackB;

    public QueueImplByStack() {
        stackA = new ArrayStack<E>();
        stackB = new ArrayStack<E>();
    }

    public void offer(E element) {
        stackA.push(element);
    }

    public E poll() {
        if (stackA.isEmpty()) {
            throw new NullPointerException("queue is null");
        }
        while (stackA.size() != 1) {
            stackB.push(stackA.pop());
        }
        E ret = stackA.pop();
        //把B中的元素全部放回A
        while (!stackB.isEmpty()) {
            stackA.push(stackB.pop());
        }
        return ret;
    }

    public E element() {
        if (stackA.isEmpty()) {
            throw new NullPointerException("queue is null");
        }
        while (stackA.size() != 1) {
            stackB.push(stackA.pop());
        }
        E ret = stackA.peeK();
        //把B中的元素全部放回A
        while (!stackB.isEmpty()) {
            stackA.push(stackB.pop());
        }
        return ret;
    }

    @Override
    public String toString() {
        if (stackA.isEmpty()) {
            return "[]";
        } else {
            return stackA.toString();
        }
    }
}
