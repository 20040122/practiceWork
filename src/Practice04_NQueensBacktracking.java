import java.util.ArrayList;
import java.util.List;

public class Practice04_NQueensBacktracking {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n]; // 用于存储每行皇后所在的列索引
        backtrack(n, 0, queens, result);
        return result;
    }

    private static void backtrack(int n, int row, int[] queens, List<List<String>> result) {
        if (row == n) {
            // 构建棋盘
            List<String> board = generateBoard(queens, n);
            result.add(board);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(queens, row, col)) {
                queens[row] = col; // 在当前行的col列放置皇后
                backtrack(n, row + 1, queens, result); // 递归放置下一行的皇后
            }
        }
    }

    private static boolean isValid(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            // 检查是否与之前的皇后冲突
            if (queens[i] == col || queens[i] - i == col - row || queens[i] + i == col + row) {
                return false;
            }
        }
        return true;
    }

    private static List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (queens[i] == j) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            board.add(sb.toString());
        }
        return board;
    }

    public static void main(String[] args) {
        int n = 6;
        List<List<String>> solutions = solveNQueens(n);
        for (List<String> solution : solutions) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
