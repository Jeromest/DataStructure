package part03.PartitionAndBacktrackingAlgorithm;

public class BigIntegerMultiplication {
    public static void main(String[] args) {
        long x = 12345678;
        long y = 12345678;
        long product = multi(x, y, 4);
        System.out.println(product);
    }

    private static long multi(long x, long y, int n) {  //n表示深度
        if (x == 0 || y == 0) {
            return 0;
        }
        if (n == 1) {
            return x * y;
        }
        long A = (long) (x / Math.pow(10, n / 2));
        long B = (long) (x - A * Math.pow(10, n / 2));
        long C = (long) (y / Math.pow(10, n / 2));
        long D = (long) (y - C * Math.pow(10, n / 2));
        long E = multi(A, C, n / 2);
        long F = multi(B, D, n / 2);
        long G = multi(A + B, C + D, n / 2);
        return (long) (E * Math.pow(10, n) + (G - E - F) * Math.pow(10, n / 2) + F);
    }
}
