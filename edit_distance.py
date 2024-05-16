#Approach 1:
# Time Complexity : O(m*n)
# Space Complexity : O(m*n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if word1 == word2:
            return 0
        
        m = len(word1)
        n = len(word2)
        dp = [[0]*(n+1) for _ in range(m+1)]
        
        #top row
        for j in range(0, n+1):
            dp[0][j] = j
        
        for i in range(1, m+1):
            for j in range(0, n+1):
                #first colum
                if j == 0:
                    dp[i][j] = i
                else:
                    if word1[i-1] == word2[j-1]:
                        dp[i][j] = dp[i-1][j-1]
                    else:
                        dp[i][j] = min(dp[i][j-1], min(dp[i-1][j], dp[i-1][j-1])) + 1
        
        return dp[m][n]

#Approach 2: Optimized on space
# Time Complexity : O(m*n)
# Space Complexity : O(n)
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if word1 == word2:
            return 0
        m = len(word1)
        n = len(word2)
        dp = [0]*(n+1)
        
        #top row
        for j in range(0, n+1):
            dp[j] = j
        
        diagUp = 0
        for i in range(1, m+1):
            for j in range(0, n+1):
                temp = dp[j]
                #first colum
                if j == 0:
                    dp[j] = i
                else:
                    if word1[i-1] == word2[j-1]:
                        dp[j] = diagUp
                    else:
                        dp[j] = min(dp[j-1], min(dp[j], diagUp)) + 1
                
                diagUp = temp
        
        return dp[n]

        