class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        n1 = len(word1)
        n2 = len(word2)
        
        
        dp = [[0 for x in range(n1+1)] for y in range(n2+1)]
        
        for i in range(len(dp[0])):
            dp[0][i] = i
            
        for i in range(len(dp)):
            dp[i][0] = i
        print(dp)
        
        for i in range(1,len(dp)):
            for j in range(1,len(dp[0])):
                if word2[i-1] == word1[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = 1+ min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j])
        
        return dp[-1][-1]
