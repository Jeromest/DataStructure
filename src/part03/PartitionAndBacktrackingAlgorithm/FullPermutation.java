package part03.PartitionAndBacktrackingAlgorithm;

import java.util.TreeSet;

public class FullPermutation {
    private static TreeSet<String> set = new TreeSet<>();
    public static void main(String[] args) {
        String s = "ABB";
        char[] arr = s.toCharArray();
        permutation(arr, 0, arr.length - 1);
        for (String str : set) {
            System.out.println(str);
        }
    }

    private static void permutation(char[] arr, int from, int to) {
        if (from == to) {
            set.add(String.valueOf(arr));
        }
        for (int i = from; i <= to; i++) {
            swap(arr, i, from);
            permutation(arr, from + 1, to);
            swap(arr, i, from);
        }
    }

    private static void swap(char[] arr, int a, int b) {
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
