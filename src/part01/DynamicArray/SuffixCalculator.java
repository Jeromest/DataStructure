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
