# Problem 2 : Regular Expression Matching
# Time Complexity :
'''
2-d array - O(m*n) where m is the length of s and n is the length of p
1-d array - O(m*n) where m is the length of s and n is the length of p
'''
# Space Complexity :
'''
2-d array - O(m*n) where m is the length of s and n is the length of p
1-d array - O(n) where n is the length of p
'''
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this :
'''
None
'''

# Your code here along with comments explaining your approach
# 2-d array

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        # get the length of s and p string
        m = len(s)
        n = len(p)
        # define dp matrix with size(m+1*n+1) and fill with False
        dp = [[False] * (n+1) for _ in range(m+1)]
        # set the value of 0th row and 0th column as True
        dp[0][0] = True
        # loop from 1 to n+1
        for j in range(1, n+1):
            # get the character at (j-1)th position of p string
            pChar = p[j-1]
            # if the character is * then copy the value at 2 steps back
            if pChar == '*':
                dp[0][j] = dp[0][j-2]
        # loop from 1 to (m+1)
        for i in range(1, m+1):
            # loop from 1 to (n+1)
            for j in range(1, n+1):
                # get the char of s at(i-1)th position and char of p at (j-1)th position
                sChar = s[i-1]
                pChar = p[j-1]
                # if the p char is not *
                if pChar != '*':
                    # check if both the character are equal or the character of p is .
                    if (sChar == pChar or pChar == '.'):
                        # if it is then just copy the value of diagonally up left from the dp matrix
                        dp[i][j] = dp[i-1][j-1]
                    else:
                        # else set the value as False
                        dp[i][j] = False
                else:
                    # if the character at p is * then first check if the current character of s and preceeding(j-2)th character of p is equal or (j-2)th character of p is .
                    if(sChar == p[j-2] or p[j-2] == '.'):
                        # then the value is value of two step back or value of one step above
                        dp[i][j] = dp[i][j-2] or dp[i-1][j]
                    else:
                        # else the value is value two step back
                        dp[i][j] = dp[i][j-2]
        # return the value at mth and nth position of dp matrix
        return dp[m][n] 

# 1-d array
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        # get the length of s and p string
        m = len(s)
        n = len(p)
        # define dp matrix with size(n+1) and fill with False
        dp = [False] * (n+1)
        # set the value of 0th element as True
        dp[0] = True
        # loop from 1 to n+1
        for j in range(1, n+1):
            # get the character at (j-1)th position of p string
            pChar = p[j-1]
            # if the character is * then copy the value at 2 steps back
            if pChar == '*':
                dp[j] = dp[j-2]

        # loop from 1 to (m+1)
        for i in range(1, m+1):
            # set the diagonalUp to dp[0]
            diagonalUp = dp[0]
            # set the dp[0] to False
            dp[0] = False
            # loop from 1 to (n+1)
            for j in range(1, n+1):
                # get the char of s at(i-1)th position and char of p at (j-1)th position
                sChar = s[i-1]
                pChar = p[j-1]
                # set the temp to dp[j] value 
                temp = dp[j]
                # if the p char is not *
                if pChar != '*':
                    # check if both the character are equal or the character of p is .
                    if (sChar == pChar or pChar == '.'):
                        # if it is then just copy the value of diagonally up left from the dp array
                        dp[j] = diagonalUp
                    else:
                        # else set the value as False
                        dp[j] = False
                else:
                    # if the character at p is * then first check if the current character of s and preceeding(j-2)th character of p is equal or (j-2)th character of p is .
                    if(sChar == p[j-2] or p[j-2] == '.'):
                        # then the value is value of two step back or value of one step above
                        dp[j] = dp[j-2] or dp[j]
                    else:
                        # else the value is value two step back
                        dp[j] = dp[j-2]
                # set the diagonalUp to temp variable
                diagonalUp = temp
        # return the value at nth position of dp array
        return dp[n] 
