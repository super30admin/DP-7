# Time Complexity : O(SP)
# Space Complexity : O(SP)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach
# Using DP Approach


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if not s and not p:
            return True
        dp = [[False for j in range(len(p) + 1)] for i in range(len(s) + 1)]

        dp[0][0] = True
        #top row
        for j in range(1, len(p) + 1):
            if p[j - 1] == "*":
                dp[0][j] = dp[0][j - 2]

        for i in range(1, len(s) + 1):
            for j in range(1, len(p) + 1):
                if p[j - 1] != "*":
                    if p[j - 1] == s[i - 1] or p[j - 1] == ".":
                        dp[i][j] = dp[i - 1][j - 1]
                else:
                    #zero case
                    dp[i][j] = dp[i][j - 2]
                    #one case if available
                    #if current character matches preceeding character
                    if p[j - 2] == s[i - 1] or p[j - 2] == ".":
                        if dp[i - 1][j] == True:
                            dp[i][j] = True
        return dp[-1][-1]
