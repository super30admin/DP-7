#Time complexity: O(mn)
#Space complexity: O(mn)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m,n=len(s),len(p)
        dp=[[False for i in range(n+1)] for j in range(m+1)]
        dp[0][0]=True
        
        for i in range(1,n+1):
            if p[i-1]=="*":
                dp[0][i]=dp[0][i-2]
        
        for i in range(1,m+1):
            for j in range(1,n+1):
                
                # if char in p is not *
                if(p[j-1]!="*"):
                    if(s[i-1]==p[j-1] or p[j-1]=="."):
                        dp[i][j]=dp[i-1][j-1]
                
                #if char is *
                else:
                    #not choose
                    dp[i][j]=dp[i][j-2]
                    
                    #choose
                    if s[i-1]==p[j-2] or p[j-2]==".":
                        dp[i][j]=dp[i][j] or dp[i-1][j]
 
        return dp[-1][-1]
                        
                    
                    
        