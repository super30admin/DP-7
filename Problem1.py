# Time Complexity: O (n*m)(Where n and m is length of words.) 
# Space Complexity: O(1)
# Did this code successfully run on Leetcode : Yes
# This problem can be solved using dynamic programing.
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)
        n = len(word2)
#       Initialize n * m array with 0
        dp = [[0 for i in range(m+1)] for j in range(n+1)]
        
#       Set first row and column as distance from empty string.
        for i in range(m+1):
            dp[0][i] = i
            
        for j in range(n+1):
            dp[j][0] = j

#       Fill rest of the cells by finding minimum number of opertions from insert, update and delete.
        for i in range(1, n+1):
            for j in range(1, m+1):
                insert = 1 + dp[i][j-1]
                delete = 1 + dp[i-1][j]
                update = 1 + dp[i-1][j-1]
#               If both charcter are same we don't need any operation on it.
                if word1[j-1] == word2[i-1]:
                    update -= 1
                dp[i][j] = min(insert, delete, update)

        return dp[n][m]
