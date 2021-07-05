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
