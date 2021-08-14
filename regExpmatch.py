# Time Complexity : O(M*N)
# Space Complexity : O(M*N)
# Did this code successfully run on Leetcode : YES
# Any problem you faced while coding this : NO

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        
        m = len(s)
        n = len(p)
        dp = [[False] * (n + 1) for i in range(m + 1)]
        
        #- and - is True (empty string)
        dp[0][0] = True
        
        #first row
        for j in range(1,len(dp[0])):
            # if * then get value from -2 pos
            if p[j-1]=='*':
                dp[0][j] = dp[0][j-2]
        
        
        for i in range(1,len(dp)):
            for j in range(1,len(dp[0])):
                if p[j-1]!='*':
                    #if same char then copy res from diagonal
                    if p[j-1]==s[i-1] or p[j-1]=='.':
                        dp[i][j]=dp[i-1][j-1]
                else:
                    dp[i][j]=dp[i][j-2]
                     #if 1 case available   
                    if p[j-2]==s[i-1] or p[j-2]=='.':
                        dp[i][j] = dp[i][j] or dp[i-1][j]
                
            
        return dp[m][n]
