#Time complexity: O(m*n)
#Space complexity: O(m*n)
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word2), len(word1)
        
        dp = [[0 for _ in range(n+1)] for _ in range(m+1)]
        
        for j in range(1, n+1):
            dp[0][j] = dp[0][j-1] + 1
        for i in range(1, m+1):
            dp[i][0] = dp[i-1][0]+1
        
        for i in range(1, m+1):
            for j in range(1, n+1):
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])+1
        return dp[m][n]
        
        
