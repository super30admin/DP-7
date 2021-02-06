class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        #O(mn) for both
        m=len(s)
        n=len(p)
        dp=[[False for i in range(n+1)] for j in range(m+1)] 
        dp[0][0]=True
        for i in range(1,n+1):
            if p[i-1]=="*":
                dp[0][i]=dp[0][i-2]
        for i in range(1,m+1):
            for j in range(1,n+1):
                if p[j-1]!="*":
                    if p[j-1]==s[i-1] or p[j-1]==".":
                        dp[i][j]=dp[i-1][j-1]
                else:
                    dp[i][j]=dp[i][j-2]
                    if s[i-1]==p[j-2] or p[j-2]==".":
                        if dp[i-1][j]:
                            dp[i][j]=dp[i-1][j]
        return dp[m][n]
                            
                        
                