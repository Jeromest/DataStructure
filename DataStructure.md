## 数据结构

#### 1、数据结构

##### 1.1、主要结构：

逻辑结构：线性结构、树形结构、图形结构

物理结构：顺序存储结构、链式存储结构

#### 2、算法

##### 2.1、基础算法：

**查找算法**：线性查找、二分查找、插值查找、斐波那契查找

**排序算法**：冒泡排序、选择排序、插入排序、希尔排序、归并排序、快速排序、堆排序、计数排序、基数排序

**图论算法**：深度优先遍历、广度优先遍历、最小生成树、最短路径、拓扑排序、关键路径

##### 2.2、进阶算法：

分治回溯、动态规划、背包算法、贪心算法、数论算法

#### 3、时间复杂度

计算方法：忽略常数，只保留幂高项，且忽略幂高项的系数

##### 3.1、常数阶O(1)

就是那些无循环、无递归、与问题输入规模N无关的、逐行执行的代码，图像为一条直线

例如：

```c
int a = 3;
int b = 4;
int c = a + b;
```

##### 3.2、线性阶O(n)

与问题输入规模有关的，主要是一层循环的代码，多个一层循环可以并列但不能包含

例如：

```c
int N = 10;
for(int i = 0; i <= N; i++){
    System.out.println(i);
}
//两者并列
for(int i = 0; i <= N; i++){
    System.out.println(i);
}
```

****

##### 3.3、线性阶O(n+m)

与O(n)一样，只不过有两种数据输入的规模

例如：

```c
int N = 10;
for(int i = 0; i <= N; i++){
    System.out.println(i);
}
for(int j = 0; j <= N; j++){
    System.out.println(j);
}
```

##### 3.4、平方阶O(n²)、O(nm)

与问题输入规模有关，主要是二层嵌套循环的代码

例如：

```c
int N = 10;
for(int i = 0; i <= N; i++){
    for(int j = 0; j <= N; j++){
    	System.out.println(i + j);
	}
}
```

##### 3.5、对数阶O(logⁿ)

与问题输入规模有关，主要是一层循环迭代或递归的代码，如二分查找

例如：

```c
int count = 1;
int N = 100000;
while(count < N)
    count = count * 2;
```

#### 4、动态数组

动态数组就是顺序存储结构具体实现的核心思想





![Iterable ](C:\Users\Jerome\Desktop\Iterable .png)

#### 5、线性表

##### 5.1、线性表的实现



#### 6、栈

##### 6.1、定义

栈是限定仅在**表尾进行插入和删除操作**的**线性表**

- 我们把允许插入和删除的一端称为**栈顶**(top)， 另一端称为**栈底**(bottom)
- 不含任何数据元素的栈称为空栈
- 栈又称为**后进先出**（Last In First Out）的线性表，简称**LIFO**结构
- 栈本身是一个线性表，其数据元素具有线性关系，只不过它是一种特殊的线性表而已
- 栈的插入操作，叫做进栈，也称压栈、入栈
- 栈的删除操作，叫做出栈，也称弹栈

##### 6.2、栈的实现

在线性表的基础上实现





##### 6.3、应用

###### 6.3.1、十进制转十六进制

输入任意一个非负十进制整数，打印输出与其等值的十六进制数

```java
package part01.DynamicArray;

import java.util.Scanner;

public class DecToHex {
    public static void main(String[] args) {
        //1.获取用户的输入 非负整数
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number:");
        int number = scanner.nextInt();

        //2.创建一个栈 用来存储余数    存的是字符数据
        ArrayStack<Character> stack = new ArrayStack<Character>();

        //3.开始计算
        int mod;
        while (number != 0) {
            mod = number % 16;  //获取一个余数 0~9 10~15 -> A~F
            if (mod < 10) {//数字0~9
                //'0' + 3  表示的是字符'0'之后第3个位置的字符'3'的编码
                stack.push((char) ('0' + mod));
            } else {//数字10~15 -> A~F
                //'A' + 11 - 10 = 'A' + 1 表示的是字符'A'之后第1个位置的字符'B'的编码
                //'A' + 15 - 10 = 'A' + 1 表示的是字符'A'之后第5个位置的字符'F'的编码
                stack.push((char) ('A' + mod - 10));
            }
            number /= 16;
        }
        //4.按照顺序弹栈即可
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}
```

###### 6.3.2、十六进制转十进制

```java
package part01.DynamicArray;

import java.util.Scanner;

public class hexToDec {
    public static void main(String[] args) {
        //1.获取用户输入一个十六进制的字符串
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string:");
        String hex = scanner.nextLine();
        //2.创建一个栈 存储每一个字符
        ArrayStack<Character> stack = new ArrayStack<>();
        //3.将字符串中的字符依次进栈
        for (int i = 0; i < hex.length(); i++) {
            stack.push(hex.charAt(i));
        }
        //4.依次弹栈 并累加计算的结果
        int sum = 0;
        int exponent = 0;//幂数
        char c;
        while (!stack.isEmpty()) {
            c = stack.pop();
            sum += getNumber(c) * Math.pow(16, exponent);
            exponent++;
        }
        System.out.println(sum);
    }

    private static int getNumber(char c) {
        //合法的范围 '0'~'9' || 'A'~'F'
        //验证字符是否合法
        if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'F')) {
            throw new IllegalArgumentException("wrong identifier by " + c);
        }
        if (c >= '0' && c <= '9') {
            //转换数字字符为数字
            return c - '0';
        } else {
            //转换字母字符为数字
            return c - 'A' + 10;
        }
    }
}
```

###### 6.3.3、回文判断

顺序和倒序意思相同的字符串，例如：上海自来水来自海上

```java
package part01.DynamicArray;

import java.util.Scanner;

public class JudgingPalindrome {
    public static void main(String[] args) {
        //1.获取用户的输入 字符串
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a text:");
        String text = scanner.nextLine();
        //2.创建一个栈  字符数据
        ArrayStack<Character> stack = new ArrayStack<>();
        //3.遍历字符串 并做出栈和入栈的操作
        char c;
        for (int i = 0; i < text.length(); i++) {
            //当前text的长度是否为奇数
            if (text.length() % 2 == 1 && i == text.length() / 2) {
                continue;
            }
            c = text.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);//如果栈为空 直接进
            } else {
                if (c == stack.peeK()) {//栈顶是否和当前字符c相等
                    stack.pop();//相等 相消 直接弹栈
                } else {
                    stack.push(c);//不相等 直接进
                }
            }
        }
        //如果栈为空 则是回文 否则不是
        if (stack.isEmpty()) {
            System.out.println(text + " is a palindrome");
        } else {
            System.out.println(text + " is not a palindrome");
        }
    }
}
```



###### 6.3.4、括号匹配

输入一行括号，判断是否匹配，例如："{[<>]}"，"{[(]>}"

```java
package part01.DynamicArray;

import java.nio.channels.Channel;

//匹配括号
public class MatchBracket {
    public static void main(String[] args) {
        String text = "{[<>]()}";
        //1.创建一个栈
        ArrayStack<Character> stack = new ArrayStack<>();
        //2.开始遍历text取出字符 做出栈和入栈操作
        char c;
        char top;
        for (int i = 0; i < text.length(); i++) {
            c = text.charAt(i);
            //如果栈为空  直接进
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                top = stack.peeK();
                if (top - c == -1 || top - c == -2) {
                    stack.pop();
                } else {
                    stack.push(c);
                }

            }
        }
        if (stack.isEmpty()) {
            System.out.println("match!");
        } else {
            System.out.println("mismatch!");
        }

    }
}
```

###### 6.3.5、中缀表达式计算器

```java
package part01.DynamicArray;

//中缀表达式计算器

/**
 * 操作符栈
 * 数字栈
 * 如果遇到数字 直接进数字栈
 * 如果遇到操作符
 *   1.如果操作符栈为空 直接进
 *   2.如果操作符栈顶为 "("  直接进
 *   3.如果操作符栈顶为其他操作符
 *          当前的操作符比栈顶的操作符的优先级大 直接进
 *          当前的操作符比栈顶的操作符的优先级等于或小于 将操作符栈中比当前操作符优先级大的所有操作符处理
 *          直到空栈 "(" 小优先级的操作符为止
 *   4.当前操作符是 ")" 将操作符栈中 "(" 上面的所有操作符处理掉 最终 "(" 弹栈即可
 */
public class InfixCalculator {
    public static void main(String[] args) {
        String expression = "(10+20/2*3)/2+8";
        //格式化表达式
        expression = insertBlanks(expression);
        String[] tokens = expression.split("#");

        //数字栈
        ArrayStack<Integer> numberStack = new ArrayStack<>();
        //操作符栈
        ArrayStack<Character> operatorStack = new ArrayStack<>();

        //特别注意空字符串
        for (String token : tokens) {
            //过滤空字符串
            if (token.length() == 0) {
                continue;

            } else if (token.charAt(0) =='+' || token.charAt(0) =='-') {
                //如果遇到"+"或"-" 让操作符栈中之前所有的操作符处理
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peeK() == '+' || operatorStack.peeK() == '-' ||
                                operatorStack.peeK() == '*' || operatorStack.peeK() == '/')) {
                    processAnOperator(numberStack, operatorStack);
                }

                operatorStack.push(token.charAt(0));
                //如果遇到"*"或"/" 让操作符栈中之前的"*"和"/"处理掉
            } else if (token.charAt(0) =='*' || token.charAt(0) =='/') {

                while (!operatorStack.isEmpty() &&
                        (operatorStack.peeK() == '*' || operatorStack.peeK() == '/')) {
                    processAnOperator(numberStack, operatorStack);
                }
                operatorStack.push(token.charAt(0));

            } else if (token.charAt(0) == '(') {
                operatorStack.push('(');
            } else if (token.charAt(0) == ')') {
                //处理所有"("之前的操作符
                while (operatorStack.peeK() != '(') {
                    processAnOperator(numberStack, operatorStack);
                }
                operatorStack.pop();
            } else {
                //如果是数字直接进数字栈即可
                numberStack.push(new Integer(token));
            }
        }
        //将剩余的数字和操作符处理
        while (!operatorStack.isEmpty()) {
            processAnOperator(numberStack, operatorStack);
        }
        System.out.println(numberStack.pop());
    }

    //在数字栈中弹出两个数字  在操作符栈中弹出一个操作符  做运算
    private static void processAnOperator(ArrayStack<Integer> numberStack, ArrayStack<Character> operatorStack) {
        char op = operatorStack.pop();
        int num1 = numberStack.pop();
        int num2 = numberStack.pop();
        //num2 op num1
        if (op == '+') {
            numberStack.push(num2 + num1);
        } else if (op == '-') {
            numberStack.push(num2 - num1);
        } else if (op == '*') {
            numberStack.push(num2 * num1);
        } else if (op == '/') {
            numberStack.push(num2 / num1);
        }

    }
    
    private static String insertBlanks(String expression) {
        StringBuilder sb = new StringBuilder();
        //遍历表达式
        char c;
        for (int i = 0; i < expression.length(); i++) {
            c = expression.charAt(i);
            //如果遇到符号 则将符号的前后都加上空格 再添加在sb中
            if (c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/') {
                sb.append('#');
                sb.append(c);
                sb.append('#');
            } else {
                //如果遇到数字 则原封不动添加在sb中
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
```

###### 6.3.6、中缀转后缀：

```java
package part01.DynamicArray;

import java.util.Arrays;

public class InfixToSuffix {
    public static void main(String[] args) {
        String infixExpression = "(10+20/2*3)/2+8";
        String suffixExpression = infixToSuffix(infixExpression);
        System.out.println(suffixExpression);
    }

    private static String infixToSuffix(String infixExpression) {
        ArrayStack<String> opStack = new ArrayStack<>();
        ArrayList<String> suffixList = new ArrayList<>();
        infixExpression = insertBlanks(infixExpression);
        String[] tokens = infixExpression.split(" ");
        System.out.println(Arrays.toString(tokens));
        for (String token : tokens) {
            if (token.length() == 0) {
                continue;
            }
            if (isOperator(token)) {//如果是操作符
                while (true) {
                    //栈空 或 栈顶是"(" 直接进
                    if (opStack.isEmpty() || "(".equals(opStack.peeK())) {
                        opStack.push(token);
                        break;
                    }
                    //当前操作符的优先 > 栈顶操作符的优先级  直接进
                    if (priority(token) > priority(opStack.peeK())) {
                        opStack.push(token);
                        break;
                    }
                    //如果栈不为空  且 栈顶不是"(" 且 栈顶操作符的优先级 >= 当前的操作符
                    suffixList.add(opStack.pop());
                }

            } else if (isNumber(token)) {//如果是数字
                suffixList.add(token);
            } else if ("(".equals(token)) {//如果是"("
                opStack.push(token);
            } else if (")".equals(token)) {//如果是")"
                while (true) {
                    if ("(".equals(opStack.peeK())) {
                        opStack.pop();
                        break;
                    } else {
                        suffixList.add(opStack.pop());
                    }
                }
            } else {
                throw new IllegalArgumentException("wrong identifier " + token);
            }
        }
        while (!opStack.isEmpty()) {
            suffixList.add(opStack.pop());
        }
        StringBuilder sb = new StringBuilder();
        //10 20 2 * / +
        for (int i = 0; i < suffixList.size(); i++) {
            sb.append(suffixList.get(i) + " ");
        }
        return sb.toString();
    }

    private static int priority(String token) {
        if ("*".equals(token) || "/".equals(token)) {
            return 1;
        }
        if ("+".equals(token) || "-".equals(token)) {
            return 0;
        }
        return -1;
    }

    private static boolean isNumber(String token) {
        return token.matches("\\d+");   //  //d+匹配的是多个数字
    }

    private static boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token);
    }

    private static String insertBlanks(String expression) {
        StringBuilder sb = new StringBuilder();
        //遍历表达式
        char c;
        for (int i = 0; i < expression.length(); i++) {
            c = expression.charAt(i);
            //如果遇到符号 则将符号的前后都加上空格 再添加在sb中
            if (c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/') {
                sb.append(' ');
                sb.append(c);
                sb.append(' ');
            } else {
                //如果遇到数字 则原封不动添加在sb中
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
```

###### 6.3.7、后缀表达式计算器

也叫逆波兰表达式，将运算符写在操作数之后

中缀形式：(10+20/2*3)/2+8

后缀形式：10 20 2 / 3 * + 2 / 8 +

方便计算机计算，但对用户不友好

```java
package part01.DynamicArray;

import java.util.Arrays;

//后缀表达式计算器
public class SuffixCalculator {
    public static void main(String[] args) {
        String expression = "10 20 2 / 3 * + 2 / 8 + ";
        ArrayStack<Integer> stack = new ArrayStack<>();
        String[] tokens = expression.split(" ");
        for (String token : tokens) {
            //如果是数字  直接进
            if (isNumber(token)) {
                stack.push(new Integer(token));
            } else {
                //如果是操作符  则弹出两个数字进行计算 将新的结果入栈
                processAnOperator(stack, token);
            }
        }
        System.out.println(stack.pop());
    }

    private static void processAnOperator(ArrayStack<Integer> stack, String token) {
        int num1 = stack.pop();
        int num2 = stack.pop();
        switch (token) {
            case "+":
                stack.push(num2 + num1);
                break;
            case "-":
                stack.push(num2 - num1);
                break;
            case "*":
                stack.push(num2 * num1);
                break;
            case "/":
                stack.push(num2 / num1);
                break;
        }
    }

    private static boolean isNumber(String token) {
        return token.matches("\\d+");   //  //d+匹配的是多个数字
    }
}
```

#### 7、双端栈

##### 7.1、定义

是指将一个线性表的**两端当做栈底**分别进行入栈和出栈的操作，双端栈是线性表的一种，更是栈的一个特殊分类

主要利用了栈 “栈底位置不变，而栈顶位置动态变化“ 的特性

左栈为空：ltop = -1

左栈元素个数：ltop +1

右栈为空：rtop = length

右栈元素个数：length - rtop

栈满：ltop + 1 = rtop

##### 7.2、实现



##### 7.3、应用

###### 7.3.1、文件夹遍历

```java
package part01.DynamicArray;

import java.io.File;

//文件夹遍历
public class DirectorTraversal {
    public static void main(String[] args) {
        File dir = new File("E:\\workspace\\JavaProject\\DataStructure\\src");
        ArrayQueue<File> queue = new ArrayQueue<>();
        queue.offer(dir);
        while (!queue.isEmpty()) {
            File subDir = queue.poll();
            System.out.println("【" + subDir.getName() + "】");
            File[] subFiles = subDir.listFiles();
            for (File f : subFiles) {
                if (f.isDirectory()) {
                    queue.offer(f);
                } else {
                    System.out.println(f.getName());
                }
            }
        }
    }
}
```

###### 7.3.2、栈实现队列

```java
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
```

###### 7.3.3、队列实现栈

```java
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
```

###### 7.3.4、循环队列

队列的顺序结构本身是由ArrayList实现的

在数据元素入队的时候，相当于在ArrayList表尾添加元素

在数据元素出队的时候，相当于在ArrayList表头删除元素

很明显，入队的时间复杂度为O(1)，出队的时间复杂度为O(n)

线性表增删数据元素的时间复杂度都是O(n)，但是这个是按平均算的

实现：





###### 7.3.5、双端队列

双端队列是限定插入和删除操作在表的两端进行的线性表，是一种具有队列和栈的性质的数据结构

双端队列空时：front == rear

双端队列满时：(rear + 1) % len == front

实现：





#### 8、动态链表



##### 8.1、单向链表



##### 8.2、单向循环链表

如果把单链表的最后一个节点的指针指向链表头部，而不是指向NULL，那么就构成了一个单项循环链表

###### 8.2.1、约瑟夫环

```java
//约瑟夫环问题  内部实现
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
```

```java
package part02.DynamicLinkedList;

public class JosephusLoop {
    public static void main(String[] args) {
        LinkedSinglyCircularList<Integer> list = new LinkedSinglyCircularList<>();
        for (int i = 1; i <= 6; i++) {
            list.add(i);
        }
        System.out.println(list);
        //内部实现
        //list.josephusLoop();

        /**
         * 外部实现
         * 1 2 3 4 5 6
         * 0 1 2 3 4 5
         * p
         * p = 0
         * p = (p + 2) % size()
         */
        int p = 0;
        while (list.size() != 2) {
            p = (p + 2) % list.size();
            list.remove(p);
        }
        System.out.println(list);
    }
}

```

###### 8.2.2、逢七过

```java
package part02.DynamicLinkedList;

import part01.DynamicArray.ArrayList;

public class SevenGame {
    public static void main(String[] args) {
        int M = 5;   //M个人
        int N = 50;  //N个数，默认从1开始
        LinkedSinglyCircularList<ArrayList<String>> list = new LinkedSinglyCircularList<>();
        for (int i = 1; i <= M; i++) {
            list.add(new ArrayList<>());    //每一个ArrayList对应的就是一个玩家
        }
        //
        int index = 0;
        for (int num = 1; num <= N; num++) {
            list.get(index++ % M).add(getAnswer(num));
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("玩家%d号的回答顺序是:%s", i + 1, list.get(i)));
        }
        /**
         * 游戏改进
         * 输入：
         *  一共几位玩家？
         *  几号玩家开始？
         *  开始数字是几？
         *  你是几号玩家？
         * 输出：
         *  你的回答顺序[.......]
         */
    }

    private static String getAnswer(int num) {
        if (num % 7 == 0) {
            return "过";
        }
        if (containsSeven(num)) {
            return "过";
        }
        return num + "";
    }

    private static boolean containsSeven(int num) {
        String str = num + "";  //"17"
        return str.contains("7");
    }
}

```

##### 8.3、双向循环链表

双向链表也叫双链表，是链表的一种，它的每个数据节点中都有**两个指针**，分别指向**直接后继**和直接前驱。所以，从双链表中的任意一个节点开始，都可以很方便的访问它的前驱结点和后继节点。一般我们都构造双向循环链表。



##### 8.4、链栈



##### 8.5、链队列





# 算法

定义：一个问题的**解决流程**和**步骤**，称之为算法

分治算法、回溯算法、动态规划算法、贪心算法，并不是指某一个具体的算法

它们仅仅是**针对不同的特点问题的解决策略和思想而已**

而二分查找、冒泡排序指的是一个具体的算法

#### 1、递归

程序调用自身的编程技巧称为递归(recursion)

```java
public static void show() {
    show();
}
```

它通常把一个大型复杂的问题层层转化为一个与原问题相似的规模较小的问题来解，递归策略只需少量的程序就可以描述出结题过程所需要的多次重复计算，大大的减少了程序的代码量。

一般来说，递归所需要有**边界条件**、**递归前进段**和**递归返回段**。当边界条件不满足时，递归前进，当边界条件满足时，递归返回。

#### 2、分治算法

分治算法就是将原问题划分成n个规模较小，并且与原问题相似的子问题，递归地解决这些子问题，然后再合并其结果，就得到原问题的解。

分治算法实现过程中，每一层递归都会涉及这样三个操作：

- 分解：将原问题分解成一系列子问题
- 解决：递归地求解各个子问题，若子问题足够小，则直接求解
- 合并：将子问题的结果合并成原问题

分治算法能解决的问题，一般需要满足下面的条件：

- 原问题分解成的小问题具有相同的模式
- 原问题分解成的子问题可以独立求解，子问题之间没有相关性
- 具有分解终止条件，也就是说，当问题足够小，可以直接求解
- 可以将子问题合并成原问题，而这个合并操作的复杂度不能太高，否则就起不到减小算法总体复杂度的效果了

另外：

- 但凡能够用数学方法解决的问题，都可以使用分治思想
- 分治思想不一定使用递归结构

##### 例子：

###### 1、斐波那契数列

递归实现：

```java
private static void test05() {
    int n = 5;
    int num = fabonacci(n);
    System.out.println(num);
}

private static int fabonacci(int n) {
    if (n == 1 || n == 2) {
        return 1;
    }
    return fabonacci(n - 1) + fabonacci(n - 2);
}
```

迭代实现：

```java
private static void test06() {
    int n = 45;
    int num = getFaboncci(n);
    System.out.println(num);
}

private static int getFaboncci(int n) {
    if (n == 1 || n == 2) {
        return 1;
    }
    int first = 1;
    int second = 1;
    int cur = 0;
    for (int i = 3; i <= n; i++) {
        cur = first + second;
        first = second;
        second = cur;
    }
    return cur;
}
```

