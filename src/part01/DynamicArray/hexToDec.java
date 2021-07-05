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
