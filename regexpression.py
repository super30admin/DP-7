#Time Complexity: O(mn)
#Space Complexity: O(mn)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)
        
        dp = [[False for i in range(n+1)] for _ in range(m+1)]
        dp[0][0] = True
        
        for j in range(1,n+1):
            if p[j-1] == '*':
                dp[0][j] = dp[0][j-2]
                
        for i in range(1,m+1):
            for j in range(1,n+1):
                if p[j-1] != '*':
                    if s[i-1] == p[j-1] or p[j-1] == '.':
                        dp[i][j] = dp[i-1][j-1]
                    
                else:
                    dp[i][j] = dp[i][j-2]
                        
                    if s[i-1] == p[j-2] or p[j-2] == '.':
                        dp[i][j] = dp[i-1][j] or dp[i][j]
          
        return dp[-1][-1]
                        
                    
                    