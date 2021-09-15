# Time COmplexity: O(sp)
# Space Complexity: O(sp)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sl = len(s)
        pl = len(p)
        dp = [[False for i in range(pl + 1)] for j in range(sl + 1)]

        dp[0][0] = True

        for k in range(1, pl + 1):
            if p[k - 1] == "*" and k > 1:
                dp[0][k] = dp[0][k - 2]

        for i in range(1, sl + 1):
            for j in range(1, pl + 1):
                if p[j - 1] == s[i - 1] or p[j - 1] == ".":
                    dp[i][j] = dp[i - 1][j - 1]
                elif j > 1 and p[j - 1] == "*":
                    if p[j - 2] == "." or s[i - 1] == p[j - 2]:
                        dp[i][j] = dp[i][j - 2] or dp[i - 1][j]
                    else:
                        dp[i][j] = dp[i][j - 2]
        return dp[sl][pl]