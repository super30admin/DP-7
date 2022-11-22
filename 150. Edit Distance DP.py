class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word2)  # ros
        n = len(word1)  # horse

        dp = [[0] * (m + 1) for i in range(n + 1)]

        # filing the first row
        for j in range(len(dp[0])):
            dp[0][j] = j

        # filing the first column
        for i in range(len(dp)):
            dp[i][0] = i

        for i in range(1, n + 1):
            for j in range(1, m + 1):
                if word2[j - 1] == word1[i - 1]:
                    dp[i][j] = dp[i - 1][j - 1]
                else:
                    dp[i][j] = min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1])) + 1

        return dp[n][m]

# Dynamic Programming
# Time Complexity: O(m * n)
# Space Complexity: O(m * n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No