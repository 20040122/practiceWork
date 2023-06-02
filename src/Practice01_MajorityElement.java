public class Practice01_MajorityElement {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2,3,5};
        int majority = findMajorityElement(nums);
        System.out.println("众数是：" + majority);
        System.out.println("重数是：" + countOccurrences(nums, majority, 0, nums.length - 1));
    }

    public static int findMajorityElement(int[] nums) {
        return findMajorityElementHelper(nums, 0, nums.length - 1);
    }

    private static int findMajorityElementHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;
        //分治策略体现
        int leftMajority = findMajorityElementHelper(nums, left, mid);
        int rightMajority = findMajorityElementHelper(nums, mid + 1, right);

        if (leftMajority == rightMajority) {
            return leftMajority;
        }

        int leftCount = countOccurrences(nums, leftMajority, left, right);
        int rightCount = countOccurrences(nums, rightMajority, left, right);

        return leftCount > rightCount ? leftMajority : rightMajority;
    }

    private static int countOccurrences(int[] nums, int target, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == target) {
                count++;
            }
        }
        return count;
    }
}
