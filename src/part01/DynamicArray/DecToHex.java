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
