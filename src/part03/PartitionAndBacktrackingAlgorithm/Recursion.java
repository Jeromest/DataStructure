package part03.PartitionAndBacktrackingAlgorithm;

public class Recursion {
    public static void main(String[] args) {
        //1.递归累加  O(n)
        test01();
        //2.迭代累加  O(n)
        test02();
        //3.递归求阶乘  O(n)
        test03();
        //4.迭代求阶乘   O(n)
        test04();
        //5.递归斐波那契数列  O(2^n)
        test05();
        //6.迭代斐波那契数列  O(n)
        test06();
    }

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

    private static void test04() {
        int product = 1;
        int n = 10;
        for (int i = 1; i <= n; i++) {
            product *= i;
        }
        System.out.println(product);
    }

    private static void test03() {
        int n = 10;
        int product = factorial(n);
        System.out.println(product);
    }

    private static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }

    private static void test02() {
        int sum = 0;
        int n = 100;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    private static void test01() {
        int n = 100;
        int sum = sum(n);
        System.out.println(sum);
    }

    private static int sum(int n) {
        if (n == 1) {
            return 1;
        }
        return sum(n - 1) + n;
    }
}
