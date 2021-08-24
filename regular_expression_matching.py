# Time Complexity: O(sp)
# Space Complexity: O(sp)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        n = len(s)
        m = len(p)
        dp = [[False for i in range(m + 1)] for j in range(n + 1)]

        dp[0][0] = True
        for k in range(1, m + 1):
            if p[k - 1] == "*" and k > 1:
                dp[0][k] = dp[0][k - 2]

        for i in range(1, n + 1):
            for j in range(1, m + 1):
                if p[j - 1] == s[i - 1] or p[j - 1] == ".":
                    dp[i][j] = dp[i - 1][j - 1]
                elif j > 1 and p[j - 1] == "*":
                    if p[j - 2] == "." or s[i - 1] == p[j - 2]:
                        dp[i][j] = dp[i][j - 2] or dp[i - 1][j]
                    else:
                        dp[i][j] = dp[i][j - 2]
        return dp[n][m]