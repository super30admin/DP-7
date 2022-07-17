#Time Complexity: O(mn)
#Space Complexity: O(mn)
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)
        n = len(word2)
        if word1 == word2:
            return 0
        dp = [[0 for i in range(m+1)] for _ in range(n+1)]
        
        for j in range(1,m+1):
            dp[0][j] = j 
            
        for i in range(1,n+1):
            dp[i][0] = i
            
        for i in range(1,n+1):
            for j in range(1,m+1):
                if word2[i-1] == word1[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                    
                else:
                    dp[i][j] = min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j]) + 1
                    
        return dp[-1][-1]