class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sl = len(s)
        pl = len(p)

        if s == p:
            return True

        dp = [[None] * (pl + 1) for i in range(sl + 1)]
        dp[0][0] = True

        for j in range(1, len(dp[0])):
            if p[j - 1] == "*":
                dp[0][j] = dp[0][j - 2]

        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                if p[j - 1] != "*":
                    if p[j - 1] == s[i - 1] or p[j - 1] == ".":
                        dp[i][j] = dp[i - 1][j - 1]
                else:
                    # zero case
                    dp[i][j] = dp[i][j - 2]
                    if s[i - 1] == p[j - 2] or p[j - 2] == ".":
                        dp[i][j] = dp[i][j] or dp[i - 1][j]

        return dp[sl][pl]

# Dynamic Programming
# Time Complexity : O(s * p)
# Space Complexity: O(s * p)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
