#Time Complexity : O(m x n) 
#Space Complexity : O(m x n) 
#Did this code successfully run on Leetcode : Yes
#Any problem you faced while coding this : No

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p: return True
        m = len(s)
        n = len(p)
        dp = [[False]*(n+1) for _ in range(m+1)]

        dp[0][0] = True
        # Marking first rows where we find '*' as two elements backward value
        for j in range(1, len(dp[0])):
            if p[j - 1] == "*":
                dp[0][j] = dp[0][j - 2]
        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                if p[j - 1] != "*":
                    if p[j - 1] == s[i - 1] or p[j - 1] == ".":
                        dp[i][j] = dp[i - 1][j - 1]
                else:

                    # Zero case
                    dp[i][j] = dp[i][j - 2]

                    # One case
                    if p[j - 2] == s[i - 1] or p[j - 2] == ".":
                        dp[i][j] = dp[i][j] or dp[i - 1][j]
        return dp[m][n]