package part01.DynamicArray;


public class ArrayDoubleEndStack<E> {
    private E[] data;   //存元素的容器
    private int ltop;   //左端栈的栈顶 ltop == -1 左端栈为空  ltop + 1  表示左端栈中元素的个数
    private int rtop;   //右端栈的栈顶 rtop == length 右端栈为空  length - rtop  右端栈中元素的个数
    private static int DEFAULT_SIZE = 10;   //双端栈的默认容量

    public ArrayDoubleEndStack() {
        data = (E[]) new Object[DEFAULT_SIZE];
        ltop = -1;
        rtop = data.length;
    }

    //入栈操作
    public void push(E element, int stackId) {
        if (ltop + 1 == rtop) { //双端栈满了，需要扩容
            resize(data.length * 2);
        }
        switch (stackId) {
            case 0: //向左端栈 入栈元素
                data[++ltop] = element;
                break;
            case 1: //向右端栈 入栈元素
                data[--rtop] = element;
                break;
        }
    }

    //出栈操作
    public E pop(int stackId) {
        if (isEmpty(stackId)) {
            throw new NullPointerException("stack is null");
        }
        E ret = null;
        switch (stackId) {
            case 0:
                ret = data[ltop--];
                break;
            case 1:
                ret = data[rtop++];
                break;
        }
        //如果元素个数 <= len/4 && len > DEFAULT_SIZE 缩容
        if (size(0) + size(1) == data.length / 4 && data.length > DEFAULT_SIZE) {
            resize(data.length / 2);
        }

        return ret;
    }

    private void resize(int newLength) {
        E[] newData = (E[]) new Object[newLength];
        //先处理左端栈
        for (int i = 0; i <= ltop; i++) {
            newData[i] = data[i];
        }
        //再去处理右端栈
        int index = rtop;
        for (int i = newLength - size(1); i < newLength; i++) {
            newData[i] = data[index++];
        }
        //更新右边栈顶
        rtop = newLength - size(1);
        data = newData;
    }

    public int size(int stackId) {
        switch (stackId) {
            case 0:
                return ltop + 1;
            case 1:
                return data.length - rtop;
        }
        return -1;
    }

    //判断某一端的栈是否为空
    private boolean isEmpty(int stackId) {
        switch (stackId) {
            case 0:
                return ltop == -1;
            case 1:
                return rtop == data.length;
        }
        return false;
    }

    public E peek(int stackId) {
        if (isEmpty(stackId)) {
            throw new NullPointerException("stack is null");
        }
        switch (stackId) {
            case 0:
                return data[ltop];
            case 1:
                return data[rtop];
        }
        return null;
    }

    public void clear(int stackId) {
        switch (stackId) {
            case 0:
                ltop = -1;
                break;
            case 1:
                rtop = data.length;
                break;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("ArrayDoubleEndStack:%d/%d\n", size(0) + size(1), data.length));
        if (isEmpty(0)) {
            sb.append("leftStack:[]\n");
        } else {
            sb.append("leftStack:[");
            for (int i = 0; i <= ltop; i++) {
                sb.append(data[i]);
                if (i == ltop) {
                    sb.append(']');
                    sb.append('\n');
                } else {
                    sb.append(',');
                }
            }
        }
        if (isEmpty(1)) {
            sb.append("rightStack:[]");
        } else {
            sb.append("rightStack:[");
            for (int i = data.length - 1; i >= rtop; i--) {
                sb.append(data[i]);
                if (i == rtop) {
                    sb.append(']');
                    sb.append('\n');
                } else {
                    sb.append(',');
                }
            }
        }
        return sb.toString();
    }
}
