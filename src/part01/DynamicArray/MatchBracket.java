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
