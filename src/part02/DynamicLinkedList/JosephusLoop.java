package part02.DynamicLinkedList;

public class JosephusLoop {
    public static void main(String[] args) {
        LinkedSinglyCircularList<Integer> list = new LinkedSinglyCircularList<>();
        for (int i = 1; i <= 6; i++) {
            list.add(i);
        }
        System.out.println(list);
        //内部实现
        //list.josephusLoop();

        /**
         * 外部实现
         * 1 2 3 4 5 6
         * 0 1 2 3 4 5
         * p
         * p = 0
         * p = (p + 2) % size()
         */
        int p = 0;
        while (list.size() != 2) {
            p = (p + 2) % list.size();
            list.remove(p);
        }
        System.out.println(list);
    }
}
