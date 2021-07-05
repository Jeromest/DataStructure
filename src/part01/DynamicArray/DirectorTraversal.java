package part01.DynamicArray;

import java.io.File;

//文件夹遍历
public class DirectorTraversal {
    public static void main(String[] args) {
        File dir = new File("E:\\workspace\\JavaProject\\DataStructure\\src");
        ArrayQueue<File> queue = new ArrayQueue<>();
        queue.offer(dir);
        while (!queue.isEmpty()) {
            File subDir = queue.poll();
            System.out.println("【" + subDir.getName() + "】");
            File[] subFiles = subDir.listFiles();
            for (File f : subFiles) {
                if (f.isDirectory()) {
                    queue.offer(f);
                } else {
                    System.out.println(f.getName());
                }
            }
        }
    }
}
