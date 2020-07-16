#Time Complexity:    O(m x n) 
#Space Complexity:   O(m x n) 
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(p)
        n = len(s)
        dp = [[False for i in range(m+1)] for j in range(n+1)]
        dp[0][0] = True
        
        for c in range(1, m+1):
            if p[c-1] == '*':
                dp[0][c] = dp[0][c-2]
                
        for r in range(1, n+1):
            for c in range(1, m+1):

                if (p[c-1] == '.' or s[r-1] == p[c-1]):
                    dp[r][c] = dp[r-1][c-1]

                elif (p[c-1] == '*'):
                    dp[r][c] = dp[r][c-2]
                    if (p[c-2] == '.' or s[r-1] == p[c-2]):
                        dp[r][c] = dp[r][c] or dp[r-1][c]

                else:
                    dp[r][c] = False
        
        return dp[n][m]