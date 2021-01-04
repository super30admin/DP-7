#As taught in class using DP to solve this problem, if current char is same then copy from diagonal else take min and add 1 to it 
#Time and Space Complexity O(m*n)
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)
        n = len(word2)
        dp = [[0]*(m+1)]*(n+1)
        for i in range(1,n):
            dp[0][i] = i
        for i in range(1,m):
            dp[0][i] = i
            
        for i in range(1,m):
            for j in range(1,n):
                if word1[j-i] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = 1 + min(dp[i-1][j-1],min(dp[i-1][j],dp[i][j-1]))
        return dp[m][n]
        