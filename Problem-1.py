# Time Complexity : O(M * N)  # DP table for both strings
# Space Complexity : O(M * N) # DP matrix
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Explanation:
# dp[i][j] represents minimum operations to convert word1[:i] to word2[:j].
# If characters match, take diagonal value.
# Otherwise consider insert, delete, or replace operations.

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word1), len(word2)

        dp = [[0]*(n+1) for _ in range(m+1)]

        for i in range(m+1):
            dp[i][0] = i
        for j in range(n+1):
            dp[0][j] = j

        for i in range(1, m+1):
            for j in range(1, n+1):
                if word1[i-1] == word2[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = 1 + min(
                        dp[i-1][j],    
                        dp[i][j-1],    
                        dp[i-1][j-1]   
                    )

        return dp[m][n]