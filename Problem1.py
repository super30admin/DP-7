"""
Time Complexity: O(mn)
Space Complexity: O(n)
"""
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word1), len(word2)
        dp = list(range(n + 1))

        for i in range(1, m + 1):
            diag_up = dp[0]
            for j in range(n + 1):
                temp = dp[j]
                if j == 0:
                    dp[j] = i
                elif word1[i - 1] == word2[j - 1]:
                    dp[j] = diag_up
                else:
                    dp[j] = 1 + min(dp[j], diag_up, dp[j - 1])
                diag_up = temp

        return dp[n]
