# Time Complexity : O(m*n)
# Space Complexity : O(m*n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# using dp array to check the repeated subproblems and matching the strings

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        
        m = len(s)
        n = len(p)
        dp = [[False] * (n + 1) for i in range(m + 1)]
        dp[0][0] = True
        
        #first row
        for j in range(1,len(dp[0])):
            if p[j-1]=='*':
                dp[0][j] = dp[0][j-2]
        
        
        for i in range(1,len(dp)):
            for j in range(1,len(dp[0])):
                if p[j-1]!='*':
                    if p[j-1]==s[i-1] or p[j-1]=='.':
                        dp[i][j]=dp[i-1][j-1]
                else:
                    dp[i][j]=dp[i][j-2] 
                    if p[j-2]==s[i-1] or p[j-2]=='.':
                        dp[i][j] = dp[i][j] or dp[i-1][j]
                
            
        return dp[m][n]