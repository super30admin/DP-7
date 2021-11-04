
#Time Complexity : O(nm)
#Space Complexity : O(nm)
# Did this code successfully run on Leetcode : Yes
#Any problem you faced while coding this : No

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        n = len(word1)
        m = len(word2)
        dp = [[0]*(n+1) for _ in range(m+1)]
        
        for i in range(len(dp[0])):
            dp[0][i] = i
        
        for i in range(1, len(dp)):
            dp[i][0] = i
            for j in range(1, len(dp[0])):
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = 1+ min(dp[i][j-1], dp[i-1][j-1], dp[i-1][j])
                    
        return dp[m][n]