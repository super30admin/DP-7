# Time Complexity : O(M*N)
# Space Complexity : O(M*N)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        
        m = len(s)
        n = len(p)
        
        dp = [[False]*(n+1) for i in range(m+1)]
        
        dp[0][0] = True
        
        for i in range(0,m+1):
            for j in range(1,n+1):
                
                if i == 0 and p[j-1] == '*':
                    dp[i][j] = dp[i][j-2]
                    if len(s) == 0 and j == n:
                        return dp[i][j]
                    continue
                if len(s) == 0 and i==0:
                    continue
                
                    
                if s[i-1] == p[j-1] or p[j-1] == '.':
                    dp[i][j] = dp[i-1][j-1]
                
                elif p[j-1] == '*':
                    dp[i][j] = dp[i][j-2]
                    
                    if p[j-2] == s[i-1] or p[j-2] == '.':
                        dp[i][j] = dp[i][j-2] or dp[i-1][j]
        print(dp)  
        return dp[m][n]
                    
        