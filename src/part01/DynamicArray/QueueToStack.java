package part01.DynamicArray;

//队列实现栈
public class QueueToStack {
    public static void main(String[] args) {
        StackImplByQueue<Integer> stack = new StackImplByQueue<>();
        for (int i = 1; i <= 12; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.peek());
    }
}

class StackImplByQueue<E> {
    private ArrayQueue<E> queueA;
    private ArrayQueue<E> queueB;

    public StackImplByQueue() {
        queueA = new ArrayQueue<>();
        queueB = new ArrayQueue<>();
    }

    public void push(E element) {
        if (queueA.isEmpty() && queueB.isEmpty()) {
            queueA.offer(element);
        } else if (queueA.isEmpty()) {
            queueB.offer(element);
        } else {
            queueA.offer(element);
        }
    }

    public E pop() {
        if (queueA.isEmpty() && queueB.isEmpty()) {
            throw new NullPointerException("stack is null");
        } else if (!queueA.isEmpty()) {
            while (queueA.size() != 1) {
                queueB.offer(queueA.poll());
            }
            return queueA.poll();
        } else {
            while (queueB.size() != 1) {
                queueA.offer(queueB.poll());
            }
            return queueB.poll();
        }
    }

    public E peek() {
        if (queueA.isEmpty() && queueB.isEmpty()) {
            throw new NullPointerException("stack is null");
        } else if (!queueA.isEmpty()) {
            while (queueA.size() != 1) {
                queueB.offer(queueA.poll());
            }
            E ret = queueA.element();
            queueB.offer(queueA.poll());
            return ret;
        } else {
            while (queueB.size() != 1) {
                queueA.offer(queueB.poll());
            }
            E ret = queueB.element();
            queueA.offer(queueB.poll());
            return ret;
        }
    }

    @Override
    public String toString() {
        if (queueA.isEmpty() && queueB.isEmpty()) {
            return "[]";
        } else if (!queueA.isEmpty()) {
            return queueA.toString();
        } else {
            return queueB.toString();
        }
    }
}
