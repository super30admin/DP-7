#Time Complexity :O(nm)
#Space Complexity :O(mn) 
#Did this code successfully run on Leetcode :yes
#Any problem you faced while coding this : tried running sum.
class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        
        m=len(s)
        n=len(p)
        
        dp=[[False for i in range(n+1)] for j in range(m+1)]
        dp[0][0]=True
        for i in range(1,n+1):
            if(p[i-1]=='*'):
                dp[0][i]=dp[0][i-2]
        
        
        
        for i in range(1,m+1):
            for j in range(1,n+1):
                if(s[i-1]==p[j-1] or p[j-1]=='.'):
                    dp[i][j]=dp[i-1][j-1]
                
                elif(p[j-1]=='*'):
                    #0 case
                    dp[i][j]=dp[i][j-2]
                    #1 case
                    if(p[j-2]==s[i-1] or p[j-2]=='.'):
                        if(dp[i-1][j]):
                            dp[i][j]=True
        print(dp)
        return dp[m][n]
                        