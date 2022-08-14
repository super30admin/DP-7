"""
Approach: 2-D DP
TC: O(m*n)
SC: O(m*n)
"""
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word2), len(word1)
        
        dp =[
            [0 for _ in range(n + 1)]
            for _ in range(m + 1)
        ]
        
        # for 1st row and 1st col, edit dist will be i
        for i in range(1, n+1):
            dp[0][i] = i
        
        for i in range(1, m+1):
            dp[i][0] = i
        
        # fill the rest of the dp matrix
        for i in range(1,m+1):
            for j in range(1,n+1):
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    # Delete -> dp[i][j-1]
                    # Update -> dp[i-1][j-1]
                    # Add -> dp[i-1][j]
                    dp[i][j] = min(dp[i][j-1], dp[i-1][j-1],dp[i - 1][j]) + 1
        return dp[m][n]