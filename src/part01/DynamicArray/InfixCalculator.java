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
