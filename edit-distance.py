"""
Time Complexity : O(n*m)
Space Complexity : O(n*m)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Explaination
Use DP for this, create 2d array (int)
we can perform 3 operations on each char : update, delete, add
in matrix we can get this operations on i, j index from 

DIGOANAL UP: update  |   UP: add
LEFT       : delete  |   (i,j) element  

when we have matching char we directly take value form diagonal up
else we need to perform other operations. for that we add 1 to min from all three dirs and store
"""

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        # if len(word1) == 0 or len(word2) == 0: return  0
        m = len(word1)
        n = len(word2)
        dp = [[0]*(m+1) for i in range(n+1)]
        
        for i in range(m+1):
            dp[0][i] = i
        
        for i in range(n+1):
            dp[i][0] = i
            
        for i in range(1,n+1):
            for j in range(1,m+1):
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = 1 + min(dp[i][j-1],dp[i-1][j],dp[i-1][j-1])
        
        return dp[n][m]
        