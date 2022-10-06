# Brute force Approach
# Time complexity : O(n*m)
# Space complexity : O(n*m)
# Leetcode : Solved and submitted

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        # if the words match, then return 0
        if word1 == word2:
            return 0
        
        # find the rows and cols for the dp matrix
        rows = len(word1)
        cols = len(word2)
        
        # create a dp matrix of size rows and cols of size n*n
        dp = [[0 for _ in range(cols+1)] for _ in range(rows+1)]
        
        # fill the first row as the value of i except the first index
        for i in range(1,rows+1):
            dp[i][0] = i
        
        # fill the first col as the value of j excep the first index
        for j in range(1, cols+1):
            dp[0][j] = j
        
        # traverse over the matrix
        for i in range(1,rows+1):
            for j in range(1,cols+1):
                # if the words match, then copy the value from the diagonal left
                if word1[i-1] == word2[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    # find the minimum of 3 neighbors and add 1, 1 for any of the opearation (add, update or delete)
                    dp[i][j] = min(dp[i-1][j], min(dp[i-1][j-1],dp[i][j-1])) + 1
        
        # return the last index of dp
        return dp[rows][cols]
