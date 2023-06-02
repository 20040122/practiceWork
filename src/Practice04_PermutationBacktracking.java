import java.util.ArrayList;
import java.util.List;

public class Practice04_PermutationBacktracking {
    public static void permutation(int n, int m) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        List<Integer> permutation = new ArrayList<>();
        boolean[] used = new boolean[n];

        backtrack(nums, m, permutation, used);
    }

    private static void backtrack(List<Integer> nums, int m, List<Integer> permutation, boolean[] used) {
        if (permutation.size() == m) {
            System.out.println(permutation);
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            if (!used[i]) {
                permutation.add(nums.get(i));
                used[i] = true;

                backtrack(nums, m, permutation, used);

                used[i] = false;
                permutation.remove(permutation.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int m = 2;

        permutation(n, m);
    }
}
