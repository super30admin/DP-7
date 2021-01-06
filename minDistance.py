class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if(not word1 or not word2):
            return len(word2) or len(word1)
        dp = [[0 for _ in range(len(word1)+1)] for _ in range(len(word2)+1)]
        
        rows = len(word2)+1
        cols = len(word1)+1
        
        for i in range(rows):
            for j in range(cols):
                if i==0 or j ==0:
                    dp[i][j] = j or i
                else:
                    if word1[j-1] == word2[i-1]:
                        dp[i][j] = dp[i-1][j-1]
                    else:
                        dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])
        return dp[-1][-1]
    
    Time: O(mn)
    SPace: O(mn)
