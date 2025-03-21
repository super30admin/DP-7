#time: O(m*n)
#space: O(m*n)
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m= len(word1)+1
        n= len(word2)+1
        dp=[[float('inf') for _ in range(n)] for _ in range(m)]
        for i in range(m):
            dp[i][0]=i

        for j in range(n):
            dp[0][j]=j
        for i in range(1,m):
            for j in range(1,n):
                
                if word1[i-1] == word2[j-1]:
                    dp[i][j]= dp[i-1][j-1]
                else:
                    dp[i][j]= 1+ min(dp[i-1][j-1], min(dp[i-1][j], dp[i][j-1]))
        return dp[m-1][n-1]
