import java.util.ArrayList;
import java.util.List;

public class Practice04_sum_is_k {
    public static void subsetSum(int[] nums, int k) {
        List<Integer> subset = new ArrayList<>();
        backtrack(nums, k, 0, 0, subset);
    }

    private static void backtrack(int[] nums, int target, int sum, int start, List<Integer> subset) {
        if (sum == target) {
            // 找到了满足条件的子集
            System.out.println(subset);
        } else if (sum > target || start >= nums.length) {
            // 回溯终止条件：和大于目标值或已经遍历完所有数
            return;
        } else {
            // 选择当前数
            subset.add(nums[start]);
            backtrack(nums, target, sum + nums[start], start + 1, subset);
            subset.remove(subset.size() - 1); // 回溯

            // 不选择当前数
            backtrack(nums, target, sum, start + 1, subset);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 6, 8};
        int k = 10;

        subsetSum(nums, k);
    }
}
