"""
Time Complexity : O(m*n) where m is the length of word1 and n is the length of word2
Space Complexity : O(m*n) where m is the length of word1 and n is the length of word2

Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Your code here along with comments explaining your approach:
Every time we are allowed to do three operations: update, delete and replace
So brute force way is exhaustive, try all the three operations until we get the word2, it will have exponential time complexity. As, the exhasutive approach will have repeated sub problems we can switch to dp.

In dp matrix, 
Diagonal up -> Update
Left Element > Delete
Upword -> Replace
"""


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)  # col
        n = len(word2)  # row
        dp = [[0 for _ in range(m+1)] for _ in range(n+1)]

        # update the first row
        for j in range(m+1):
            dp[0][j] = j

        # update the first column
        for i in range(1, n+1):
            dp[i][0] = i

        for i in range(1, n+1):
            for j in range(1, m+1):
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1

        return dp[n][m]
