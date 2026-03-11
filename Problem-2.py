# Time Complexity : O(M * N)  # DP table comparing string and pattern
# Space Complexity : O(M * N) # DP matrix
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Explanation:
# dp[i][j] represents if s[:i] matches p[:j].
# '.' matches any character and '*' represents zero or more of the previous element.
# For '*', we either ignore the pattern or consume characters if they match.

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        dp = [[False]*(n+1) for _ in range(m+1)]

        dp[0][0] = True

        for j in range(2, n+1):
            if p[j-1] == '*':
                dp[0][j] = dp[0][j-2]

        for i in range(1, m+1):
            for j in range(1, n+1):
                if p[j-1] == '.' or s[i-1] == p[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                elif p[j-1] == '*':
                    dp[i][j] = dp[i][j-2]
                    if p[j-2] == '.' or p[j-2] == s[i-1]:
                        dp[i][j] = dp[i][j] or dp[i-1][j]

        return dp[m][n]