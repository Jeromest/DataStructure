package part03.PartitionAndBacktrackingAlgorithm;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.util.Scanner;

/**
 * 利用分治法，将方形棋盘分成4个部分
 * 如果该特殊点在其中的某一部分，我们就接着去递归处理即可
 * 如果不存在特殊点的部分，我们假设一个特殊点，同样递归下去即可
 * 直到全覆盖即可
 *
 *左上角的棋盘，若不存在特殊点，该棋盘的右下角为特殊点
 *右上角的棋盘，若不存在特殊点，该棋盘的左下角为特殊点
 *左下角的棋盘，若不存在特殊点，该棋盘的右上角为特殊点
 *右下角的棋盘，若不存在特殊点，该棋盘的左上角为特殊点
 *
 */
public class ChessboardCoverage {
    //定义棋盘的大小 2^k * 2^k
    private static int BOARD_SIZE = 8;
    //定义一个二维数组模拟棋盘
    private static int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    //定义一个骨牌的编号
    private static int title = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("棋盘的大小为：" + BOARD_SIZE);
        System.out.println("请输入特殊方格的行号：");
        int dr = scanner.nextInt();
        System.out.println("请输入特殊方格的列号：");
        int dc = scanner.nextInt();
        //开始递归解决问题
        chessBoard(0, 0, dr - 1, dc - 1, BOARD_SIZE);
    }

    //tr tc指的是某一个方形的左上角坐标(tr, tc)
    //dr dc指的是特殊方格的坐标(dr, dc)
    //size指当前方格的尺寸
    private static void chessBoard(int tr, int tc, int dr, int dc, int size) {

    }
}
