# time - O(mn)
# sapce - O(mn)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:

        sl = len(s)
        pl = len(p)
        dp = [[False for i in range(pl + 1)] for j in range(sl + 1)]
        dp[0][0] = True
        for i in range(1, pl):
            if p[i - 1] == '*':
                dp[0][i] = dp[0][i - 2]
        # print(dp)
        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                if p[j - 1] != '*':
                    if p[j - 1] == s[i - 1] or p[j - 1] == '.':
                        dp[i][j] = dp[i - 1][j - 1]
                else:
                    dp[i][j] = dp[i][j - 2]
                    if s[i - 1] == p[j - 2] or p[j - 2] == '.':
                        if dp[i - 1][j]:
                            dp[i][j] = True

        # print(dp)
        return dp[sl - 1][pl - 1]
