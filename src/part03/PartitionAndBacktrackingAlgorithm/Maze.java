package part03.PartitionAndBacktrackingAlgorithm;

import part02.DynamicLinkedList.LinkedList;

public class Maze {
    public static int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 0, 0, 0, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 0, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
    };
    //入口坐标
    private static int enterX = 1;
    private static int enterY = 0;
    //出口坐标
    private static int exitX = 7;
    private static int exitY = 8;
    //记录每一个路径的访问状态  false表示未访问  true表示已访问
    private static boolean[][] visited = new boolean[9][9];
    //方向变化数组
    private static int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    //定义一个栈来存储经过的路径
    private static LinkedList<String> stack = new LinkedList<>();

    public static void main(String[] args) {
        System.out.println(go(enterX, enterY));
        for (String path : stack) {
            System.out.println(path);
        }
    }

    //返回值的意思是 从(x, y)向下递归是否能够走通
    private static boolean go(int x, int y) {
        stack.push("(" + x + "," + y + ")");
        visited[x][y] = true;
        if (x == exitX && y == exitY) {
            return true;
        }
        for (int i = 0; i < direction.length; i++) {
            int newX = x + direction[i][0];
            int newY = y + direction[i][1];
            if (isInArea(newX, newY) && isRoad(newX, newY) && !visited[newX][newY]) {
                //如果以新的坐标(newX, newY)向下寻求路径能走通
                if (go(newX, newY)) {
                    return true;    //那就表示当前(x, y)向下能走通
                }
            }
        }
        stack.pop();
        return false;
    }

    private static boolean isRoad(int x, int y) {
        return maze[x][y] == 0;
    }

    private static boolean isInArea(int x, int y) {
        return x >= 0 && x < 9 && y >= 0 && y < 9;
    }
}
