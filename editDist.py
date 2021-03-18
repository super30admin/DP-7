# Time Complexity : O(M*N)
# Space Complexity : O(MN)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        #put word on top row and other word on the col
        m = len(word2)
        n = len(word1)
        dp = [[0 for i in range(n+1)] for i in range(m+1)]
        print(dp)
        for i in range(0, n+1):
            dp[0][i] = i
            
        for i in range(0, m+1):
            dp[i][0] = i
        
        for i in range(1, m+1):
            for j in range(1, n+1):
                if word2[i-1] == word1[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
        return dp[-1][-1]