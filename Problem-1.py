class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word2)+1
        n = len(word1)+1

        dp = [['' for i in range(n)]for j in range(m)]

        # // top row
        for j in range(n):
            dp[0][j] = j
            
        # //first col
        for i in range(m):
            dp[i][0] = i

        for i in range(m):
            for j in range(n):
                if(word1[j - 1] == word2[i-1]):
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = 1 + min(dp[i-1][j] , min(dp[i][j-1], dp[i-1][j-1]))

        return dp[m-1][n-1]
        