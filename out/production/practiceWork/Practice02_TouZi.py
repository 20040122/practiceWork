def max_profit(projects, budget):
    n = len(projects)
    dp = [[0] * (budget + 1) for _ in range(n)]
    allocation = [[0] * (budget + 1) for _ in range(n)]

    for i in range(n):
        for j in range(1, budget + 1):
            max_profit = 0
            max_alloc = 0
            for k in range(min(j + 1, len(projects[i]))):
                if projects[i][k] + dp[i - 1][j - k] > max_profit:
                    max_profit = projects[i][k] + dp[i - 1][j - k]
                    max_alloc = k
            dp[i][j] = max_profit
            allocation[i][j] = max_alloc

    max_profit = dp[n - 1][budget]
    distribution = [0] * n
    j = budget
    for i in range(n - 1, 0, -1):
        distribution[i] = allocation[i][j]
        j -= allocation[i][j]

    distribution[0] = j

    return max_profit, distribution

projects = [
    [0, 0.11, 0.13, 0.15, 0.21, 0.24, 0.30, 0.35],
    [0, 0.12, 0.16, 0.21, 0.23, 0.25, 0.24, 0.34],
    [0, 0.08, 0.12, 0.20, 0.24, 0.26, 0.30, 0.35]
]
budget = 7

max_profit, distribution = max_profit(projects, budget)
print("最大利润为：", max_profit)
print("分配方案为：", distribution)
