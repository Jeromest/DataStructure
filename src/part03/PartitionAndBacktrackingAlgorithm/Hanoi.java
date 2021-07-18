package part03.PartitionAndBacktrackingAlgorithm;

public class Hanoi {
    public static void main(String[] args) {
        String x = "X";
        String y = "Y";
        String z = "Z";
        hanoi(10, x, y, z);
    }

    private static void hanoi(int level, String begin, String mid, String to) {
        if (level == 1) {
            System.out.println(begin + "->" + to);
        } else {
            //前level - 1
            hanoi(level - 1, begin, to, mid);
            //第level
            System.out.println(begin + "->" + to);
            //前level - 1
            hanoi(level - 1, mid, begin, to);
        }
    }
}
