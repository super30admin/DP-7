# Time O(2*m*n)
# Space O(m*n)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        n = len(p)
        m = len(s)
        dp = [[False] * (n+1) for i in range(m+1)] # extra for - matching
        dp[0][0] = True
        for i in range(m+1):
            for j in range(n+1):
                if i == 0 and j == 0: continue
                if i == 0:
                    if p[j-1] == '*':
                        dp[i][j] = dp[i][j-2]
                    else:
                        dp[i][j] = False
                elif j == 0: continue
                else:
                    if s[i-1] == p[j-1] or p[j-1] == '.':
                        dp[i][j] = dp[i-1][j-1]
                    elif p[j-1] == '*':
                        if p[j-2] == s[i-1] or p[j-2] == '.':
                            dp[i][j] = dp[i-1][j]
                        dp[i][j] = dp[i][j-2] or dp[i][j]
        return dp[-1][-1]
                    



