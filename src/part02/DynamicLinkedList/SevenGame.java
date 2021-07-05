package part02.DynamicLinkedList;

import part01.DynamicArray.ArrayList;

public class SevenGame {
    public static void main(String[] args) {
        int M = 5;   //M个人
        int N = 50;  //N个数，默认从1开始
        LinkedSinglyCircularList<ArrayList<String>> list = new LinkedSinglyCircularList<>();
        for (int i = 1; i <= M; i++) {
            list.add(new ArrayList<>());    //每一个ArrayList对应的就是一个玩家
        }
        //
        int index = 0;
        for (int num = 1; num <= N; num++) {
            list.get(index++ % M).add(getAnswer(num));
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("玩家%d号的回答顺序是:%s", i + 1, list.get(i)));
        }
        /**
         * 游戏改进
         * 输入：
         *  一共几位玩家？
         *  几号玩家开始？
         *  开始数字是几？
         *  你是几号玩家？
         * 输出：
         *  你的回答顺序[.......]
         */
    }

    private static String getAnswer(int num) {
        if (num % 7 == 0) {
            return "过";
        }
        if (containsSeven(num)) {
            return "过";
        }
        return num + "";
    }

    private static boolean containsSeven(int num) {
        String str = num + "";  //"17"
        return str.contains("7");
    }
}
