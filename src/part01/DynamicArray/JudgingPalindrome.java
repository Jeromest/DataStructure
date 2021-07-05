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
