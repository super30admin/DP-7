"""
Time Complexity : O(mn)- where m and n are lengths of both strings respectively
Space Complexity : O(mn)- as we are making a 2D matrix as DP array
Did this code successfully run on Leetcode : I don't have leetcode Premium. I saw the code online and just
wrote down my algo
Any problem you faced while coding this : no

We would perform bottom up DP here, ie, taking the smallest problem ie an empty string and the building on it. So, we would make
a 2D matrix, keep word1 on columns and word2 or rows. At every point, we would check that how much steps would it take to 
convert one substring to another. For that, if the value on row and column are the same, we are just taking value from upper left 
diagonal, which basically denotes update. If not, we would take minimum of columns- the upper left, upper column and the left 
column and add one to it. The left column symbolises delete and the upper one addition. 
"""


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)
        n = len(word2)
        if not m or not n:
            return max(m, n)
        dp = [[0 for i in range(m+1)] for j in range(n+1)]
        for i in range(m+1):
            dp[0][i] = i
        for i in range(n+1):
            dp[i][0] = i
        for i in range(1, n+1):
            for j in range(1, m+1):
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = 1+min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])
        return dp[-1][-1]
