import java.util.Scanner;

public class Practice06_tspwork_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入城市的数量:");
        int N = sc.nextInt();
        System.out.println("请输入城市之间的距离矩阵:");
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = sc.nextInt();
            }
        }

        int[] path = new int[N];
        for (int i = 0; i < N; i++) {
            path[i] = i;
        }

        int minCost = Integer.MAX_VALUE;
        int[] bestPath = new int[N];

        do {
            int currentCost = calculatePathCost(path, dist);
            if (currentCost < minCost) {
                minCost = currentCost;
                System.arraycopy(path, 0, bestPath, 0, N);
            }
        } while (nextPermutation(path));

        System.out.println("路径花费最小为：" + minCost);
    }

    private static int calculatePathCost(int[] path, int[][] dist) {
        int cost = 0;
        int N = path.length;

        for (int i = 0; i < N - 1; i++) {
            cost += dist[path[i]][path[i + 1]];
        }

        cost += dist[path[N - 1]][path[0]]; // Return to the starting city
        return cost;
    }

    private static boolean nextPermutation(int[] array) {
        int i = array.length - 2;
        while (i >= 0 && array[i] >= array[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = array.length - 1;
        while (array[j] <= array[i]) {
            j--;
        }

        swap(array, i, j);
        reverse(array, i + 1, array.length - 1);
        return true;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void reverse(int[] array, int i, int j) {
        while (i < j) {
            swap(array, i, j);
            i++;
            j--;
        }
    }
}
