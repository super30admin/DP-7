# Problem 1 : Edit Distance
# Time Complexity : 
'''
2-d aarray - O(m*n) where m is the length word1 and n is the length of word2
1-d array - O(m*n) where m is the length word1 and n is the length of word2
'''
# Space Complexity :
'''
2-d aarray - O(m*n) where m is the length word1 and n is the length of word2
1-d array - O(n) where n is the length of word2
'''
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this :
'''
None
'''

# Your code here along with comments explaining your approach

# 2-d array
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        # get the length of word1 and word2
        m = len(word1)
        n = len(word2)
        # define dp matrix with size(m*n) and fill with 0
        dp = [[0] * (n+1) for _ in range(m+1)]

        # loop through 0th row
        for j in range(n+1):
            # set the value of dp[0][j] as j
            dp[0][j] = j
        
        # loop form 1 to m 
        for i in range(1, m+1):
            # set the value 0th column as i
            dp[i][0] = i
            # loop from 1 to n
            for j in range(1, n+1):
                # if the character at (i-1)th and (j-1)th position of both words are equal then just copy the value of diagonally up left ie none operation
                if word1[i-1] == word2[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    # else it sum of 1 and minimum of add(value of above row), update(diagonally up left) and delete(one step back)
                    dp[i][j] = 1 + min(dp[i-1][j-1], min(dp[i-1][j], dp[i][j-1]))
        # return the value at mth and nth position in dp matrix
        return dp[m][n]
    
# 1-d array
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        # get the length of word1 and word2
        m = len(word1)
        n = len(word2)
        # define dp matrix with size(n) and fill with 0
        dp = [0] * (n+1)

        # loop through 0th row
        for j in range(n+1):
            # set the value of dp[0][j] as j
            dp[j] = j
        
        # loop form 1 to m 
        for i in range(1, m+1):
            # set the diagonalUp to dp[0] which will store the previous diagonally up left value
            diagonalUp = dp[0]
            # set the value 0th element as i
            dp[0] = i
            # loop from 1 to n
            for j in range(1, n+1):
                # store the value of dp[i] in temp variable
                temp = dp[j]
                # if the character at (i-1)th and (j-1)th position of both words are equal then just copy the value of diagonally up left ie none operation
                if word1[i-1] == word2[j-1]:
                    dp[j] = diagonalUp
                else:
                    # else it sum of 1 and minimum of add(value of above row), update(diagonally up left) and delete(one step back)
                    dp[j] = 1 + min(diagonalUp, min(dp[j], dp[j-1]))
                # store the value of temp in diagonalUp
                diagonalUp = temp
        # return the value at nth position in dp array
        return dp[n]
