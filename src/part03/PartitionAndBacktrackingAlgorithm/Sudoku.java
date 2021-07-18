package part03.PartitionAndBacktrackingAlgorithm;

import java.util.Scanner;

/*
005300000
800000020
070010500
400005300
010070006
003200080
060500009
004000030
000009700
 */
public class Sudoku {
    public static void main(String[] args) {
        int[][] board = new int[9][9];
        Scanner scanner = new Scanner(System.in);
        //"005300000"
        for (int i = 0; i < board.length; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < board.length; j++) {
                board[i][j] = Integer.parseInt(line.charAt(j) + "");
            }
        }
        solve(0, 0, board);
    }

    private static void solve(int row, int col, int[][] board) {
        if (row == 9) {
            print(board);
            return;
        } else {
            if (board[row][col] == 0) {
                for (int num = 1; num <= 9; num++) {
                    if (!isExist(row, col, num, board)) {
                        board[row][col] = num;
                        solve(row + (col + 1) / 9, (col + 1) % 9, board);
                    }
                    board[row][col] = 0;
                }
            } else {
                solve(row + (col + 1) / 9, (col + 1) % 9, board);
            }
        }
    }

    private static boolean isExist(int row, int col, int num, int[][] board) {
        //同行
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == num) {
                return true;
            }
        }
        //同列
        for (int r = 0; r < 9; r++) {
            if (board[r][col] == num) {
                return true;
            }
        }
        //3*3
        int rowMin = 0;
        int colMin = 0;
        int rowMax = 0;
        int colMax = 0;

        if (row >= 0 && row <= 2) {
            rowMin = 0;
            rowMax = 2;
        }
        if (row >= 3 && row <= 5) {
            rowMin = 3;
            rowMax = 5;
        }
        if (row >= 6 && row <= 8) {
            rowMin = 6;
            rowMax = 8;
        }
        if (col >= 0 && col <= 2) {
            colMin = 0;
            colMax = 2;
        }
        if (col >= 3 && col <= 5) {
            colMin = 3;
            colMax = 5;
        }
        if (col >= 6 && col <= 8) {
            colMin = 6;
            colMax = 8;
        }

        for (int r = rowMin; r <= rowMax; r++) {
            for (int c = colMin; c <= colMax; c++) {
                if (board[r][c] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void print(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
