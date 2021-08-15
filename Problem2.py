# Time Complexity : O()
# Space Complexity : O()
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


#considering three conditions delete update and insert the values will be coming from left,top or topleft elements
# if the chars are equal it will just come from topleft diagonal as there will be no need to perform any operation
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        
        n = len(word1)
        m = len(word2)
        
        if n == 0:
            return m
        
        dp = [[0]*(n+1) for j in range(m+1)]
        #top row
        for j in range(0,len(dp[0])):
            dp[0][j] = j
        
        for i in range(0,len(dp)):
            dp[i][0] = i
            
        for i in range(1,len(dp)):
            for j in range(1,len(dp[0])):
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1]) + 1
        return dp[m][n]
                    
                    
        


