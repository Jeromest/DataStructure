package part03.PartitionAndBacktrackingAlgorithm;

public class FullPermutation {
    public static void main(String[] args) {
        String s = "ABC";
        char[] arr = s.toCharArray();
        permutation(arr, 0, arr.length - 1);
    }

    private static void permutation(char[] arr, int from, int to) {
        if (from == to) {
            System.out.println(String.valueOf(arr));
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
