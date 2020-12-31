"""
Time Complexity : O(mn)- where m and n are lengths of both strings respectively
Space Complexity : O(mn)- as we are making a 2D matrix as DP array
Did this code successfully run on Leetcode : I don't have leetcode Premium. I saw the code online and just
wrote down my algo
Any problem you faced while coding this : no

We would perform bottom up DP here, ie, taking the smallest problem ie an empty string and the building on it. So, we would make
a 2D matrix, keep pattern on columns and string on rows. If we see, we basically have 3 major rules:
1. If the chars match or if char at current value in pattern is a '.', then its a match, then we can take boolean value
from left upper value.
If value in pattern is a '*', then we have 2 choices, either we can select it or not.If we choose not to select it, the value would
be the value 2 column to the left in same row. If we choose to select it, then we need to check the character previous to the '*'.
If its same to char in the string or a '.', we can take value from above. If any one is True, we put in True.
"""


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)
        dp = [[False for i in range(n+1)] for j in range(m+1)]
        dp[0][0] = True
        for i in range(1, n+1):
            val = ord(p[i-1])
            if not (p[i-1] == '.' or (val >= 97 and val <= 122)):
                dp[0][i] = dp[0][i-2]
        for i in range(1, m+1):
            for j in range(1, n+1):
                if p[j-1] == s[i-1] or p[j-1] == '.':
                    dp[i][j] = dp[i-1][j-1]
                elif p[j-1] == '*':
                    dp[i][j] = dp[i][j-2]
                    if p[j-2] == s[i-1] or p[j-2] == '.':
                        dp[i][j] = dp[i][j] or dp[i-1][j]
        return dp[-1][-1]
