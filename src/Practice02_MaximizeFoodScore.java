public class Practice02_MaximizeFoodScore {
    public static void main(String[] args) {
        int[] prices = {10, 15, 20}; // 菜品价格数组
        int[] scores = {5, 8, 10}; // 菜品评分数组
        int budget = 30; // 报销额度
        int maxScore = getMaxScore(prices, scores, budget);
        System.out.println("最大评价分数为：" + maxScore);
    }

    public static int getMaxScore(int[] prices, int[] scores, int budget) {
        int n = prices.length;
        int[][] dp = new int[n + 1][budget + 1];

        // 填充动态规划表
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= budget; j++) {
                if (prices[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - prices[i - 1]] + scores[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][budget];
    }
}
