# Time Complexity : O(mn), where m and n are the lengths of s and p, respectively.
# Space Complexity : O(mn)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        dp = [[False] * (n+1) for _ in range(m+1)]
        dp[0][0] = True
        
        # Initialize first column of dp matrix
        for i in range(1, m+1):
            dp[i][0] = False
        
        # Initialize first row of dp matrix
        for j in range(1, n+1):
            if p[j-1] == '*':
                dp[0][j] = dp[0][j-2]
            else:
                dp[0][j] = False
        
        # Fill the dp matrix
        for i in range(1, m+1):
            for j in range(1, n+1):
                if s[i-1] == p[j-1] or p[j-1] == '.':
                    dp[i][j] = dp[i-1][j-1]
                elif p[j-1] == '*':
                    dp[i][j] = dp[i][j-2]
                    if s[i-1] == p[j-2] or p[j-2] == '.':
                        dp[i][j] = dp[i][j] or dp[i-1][j]
                else:
                    dp[i][j] = False
        
        return dp[m][n]