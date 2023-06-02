import java.util.ArrayList;
import java.util.List;

public class Practice01_TreeFindMax {
    int maxSum;
    List<Integer> maxPath;

    public static void main(String[] args) {
        Practice01_TreeNode root = new Practice01_TreeNode(5);
        root.left = new Practice01_TreeNode(2);
        root.right = new Practice01_TreeNode(4);
        root.left.right = new Practice01_TreeNode(3);
        root.right.left = new Practice01_TreeNode(1);
        root.right.right = new Practice01_TreeNode(6);
        Practice01_TreeFindMax t = new Practice01_TreeFindMax();
        t.maxPathSum(root);
        System.out.println("路径：" + t.maxPath);
        System.out.println("路径和最大为：" + t.maxSum);
    }

    public int maxPathSum(Practice01_TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPath = new ArrayList<>();
        maxPathSumHelper(root, new ArrayList<>());
        return maxSum;
    }

    public void maxPathSumHelper(Practice01_TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }

        path.add(root.val);

        if (root.left == null && root.right == null) {
            int sum = calculateSum(path);
            if (sum > maxSum) {
                maxSum = sum;
                maxPath = new ArrayList<>(path);
            }
        }

        maxPathSumHelper(root.left, new ArrayList<>(path));
        maxPathSumHelper(root.right, new ArrayList<>(path));

        path.remove(path.size() - 1);
    }

    public int calculateSum(List<Integer> path) {
        int sum = 0;
        for (int num : path) {
            sum += num;
        }
        return sum;
    }
}