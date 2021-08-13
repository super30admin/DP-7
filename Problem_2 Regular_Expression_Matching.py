# // Time Complexity : O(n^2)
# // Space Complexity : O(n)
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : No
#
#
# // Your code here along with comments explaining your approach

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sl = len(s)
        pl = len(p)
        dp = [[False] * (pl + 1) for i in range(sl + 1)]
        dp[0][0] = True
        for j in range(1, len(dp[0])):
            # Char is *
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 2]

        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                # Not a star
                if p[j - 1] != '*':
                    # If Characters match, then look diagonal
                    if p[j - 1] == s[i - 1] or p[j - 1] == '.':
                        dp[i][j] = dp[i - 1][j - 1]
                else:
                    dp[i][j] = dp[i][j - 2]
                    # if 1 case is available
                    if p[j - 2] == s[i - 1] or p[j - 2] == '.':
                        dp[i][j] = dp[i][j] or dp[i - 1][j]
        return dp[sl][pl]