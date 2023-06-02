public class Practice01_InversionCount {
    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 5, 2};
        int inversionCount = countInversions(nums);
        System.out.println("逆序对个数：" + inversionCount);
    }

    public static int countInversions(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int[] temp = new int[nums.length];
        return mergeSortAndCount(nums, temp, 0, nums.length - 1);
    }

    private static int mergeSortAndCount(int[] nums, int[] temp, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            count += mergeSortAndCount(nums, temp, left, mid);
            count += mergeSortAndCount(nums, temp, mid + 1, right);
            count += mergeAndCount(nums, temp, left, mid, right);
        }
        return count;
    }

    private static int mergeAndCount(int[] nums, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        int count = 0;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
                count += mid - i + 1; // Increment count by the number of inversions
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        while (j <= right) {
            temp[k++] = nums[j++];
        }

        System.arraycopy(temp, left, nums, left, right - left + 1);
        return count;
    }
}

