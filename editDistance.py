# Time Complexity : O(M*N)
# Space Complexity : O(M*N)
# Did this code successfully run on Leetcode : YES
# Any problem you faced while coding this : NO

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        n = len(word1)
        m = len(word2)
        if n == 0:
            return len(word2)
        dp = [[0] * (m + 1) for i in range(n + 1)]

        # Top row
        for j in range(len(dp[0])):
            dp[0][j] = j

        for i in range(len(dp)):
            dp[i][0] = i

        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                if word1[i - 1] == word2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1]
                else:
                    dp[i][j] = min(dp[i][j - 1], min(dp[i - 1][j - 1], dp[i - 1][j])) + 1

        return dp[n][m]

