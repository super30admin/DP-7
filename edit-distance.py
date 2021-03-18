class Solution:
    # Time, Space: O(n^2)
    def minDistance(self, word1: str, word2: str) -> int:
        dp = [[0 for i in range(0,len(word2)+1)] for j in range(0,len(word1)+1)]
        dp[0][0] = 0
        
        for i in range(0,len(word1)):
            dp[i+1][0] = 1 + dp[i][0]
        
        for j in range(0,len(word2)):
            dp[0][j+1] = 1 + dp[0][j]
        
        for i in range(1,len(dp)):
            for j in range(1, len(dp[0])):
                if(word1[i-1]==word2[j-1]):
                    dp[i][j] = dp[i-1][j-1]
                else:
                    # add a character (dp[i-1][j])
                    # delete a character dp[i-1][j]
                    # update a character dp[i-1][j-1]
                    
                    dp[i][j] = 1 + min(dp[i-1][j],dp[i][j-1], dp[i-1][j-1])
        
        return dp[-1][-1]
