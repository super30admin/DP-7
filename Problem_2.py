"""
Problem : 2

Time Complexity : 
Approach 1 - O(m*n)
Approach 2 - O(m*n)

Space Complexity : 
Approach 1 - O(m*n)
Approach 2 - O(n)

Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No


"""

# Regular Expression Matching

# Approach - 1
# DP Matrix

class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        m=len(s)
        n=len(p)

        dp=[[False for _ in range(n+1)] for _ in range(m+1)]
        dp[0][0]=True

        for i in range(1,n+1):
            c=p[i-1]
            if c=="*":
                dp[0][i]=dp[0][i-2]
        
        for i in range(1,m+1):
            for j in range(1,n+1):
                sChar=s[i-1]
                pChar=p[j-1]
                if sChar==pChar or pChar==".":
                    dp[i][j]=dp[i-1][j-1]
                elif pChar=="*":
                    # one case if available
                    if sChar==p[j-2] or p[j-2]==".":
                    # preceding characater of pattern should be equal to incoming character of source
                        dp[i][j]=dp[i][j-2] or dp[i-1][j]
                    # zero case
                    else:
                        dp[i][j]=dp[i][j-2]

        return dp[m][n]
    

# Approach - 2
# DP array
    
class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        m=len(s)
        n=len(p)
        dp=[False for _ in range(n+1)]
        dp[0]=True

        for i in range(1,n+1):
            c=p[i-1]
            if c=="*":
                dp[i]=dp[i-2]
        
        for i in range(1,m+1):

            diagUp=dp[0]
            dp[0]=False
            # diagUp=False
            # if i==1:
            #     dp[0]=False
            #     diagUp=True
            for j in range(1,n+1):
                sChar=s[i-1]
                pChar=p[j-1]
                temp=dp[j]
                if pChar=="*":
                    # one case if available
                    if sChar==p[j-2] or p[j-2]==".":
                    # preceding characater of pattern should be equal to incoming character of source
                        dp[j]=dp[j-2] or dp[j]
                    # zero case
                    else:
                        dp[j]=dp[j-2]
                else:
                    if sChar==pChar or pChar==".":
                        dp[j]=diagUp
                    else:
                        dp[j]=False
                diagUp=temp         

        return dp[n]
