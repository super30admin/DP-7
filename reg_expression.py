# // Time Complexity :O(m*n)
# // Space Complexity :O(m*n)
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no


# // Your code here along with comments explaining your approach
#o(m*n)O(m*n)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        n=len(s)
        m=len(p)
        print(m,n)
        dp=[[False for i in range(m+1)] for j in range(n+1)]
        dp[0][0]=True
        #top row, inital rpw empty string pattern, so only * can have "0" characters
        for i in range(1,m+1):
            if p[i-1]=='*':
                dp[0][i]=dp[0][i-2]
        for i in range(1,n+1):
            for j in range(1,m+1):
                
                if p[j-1]!='*':
                    if p[j-1]==s[i-1] or p[j-1]=='.':
                        dp[i][j]=dp[i-1][j-1]
                else:
                
                    dp[i][j]=dp[i][j-2]
                    print(dp[i][j])
                    if s[i-1]==p[j-2] or p[j-2]=='.':
                        
                        dp[i][j]=dp[i][j] or dp[i-1][j]
                    
                        
                    
        
                
            
        print(dp)
        print(dp[1])
        return dp[n][m]
        