# TimeComplexity:O(n^2)
# SpaceCompelxity:O(n^2)
# Approach:
# BF would be try all posssible combiantionas and check that is exuastive, if you look at the tree  you can see some repeated sub problems so DP
# DP:
#  we have 4 options  comapring 2 strings
#   1.if one has *
#   2. if one has .
#   3. if both chars are equal and 
#   4. if both chars are not equal
  

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        """
        this is dp problem

        """
        m,n=len(s),len(p)
        dp=[[False for _ in range(n+1)] for _ in range(m+1)]
        dp[0][0]=True
        for j in range(2,n+1):
            if p[j-1]=="*":
                
                    
                dp[0][j]=dp[0][j-2]
        
        for i in range(1,m+1):
            for j in range(1,n+1):
                if p[j-1]=="*":
                    dp[i][j] = dp[i][j - 2]
                    if p[j-2]==s[i-1] or p[j-2]==".":
                        dp[i][j]=dp[i][j-2] or dp[i-1][j]


                elif s[i-1]==p[j-1] or p[j-1]==".":
                    dp[i][j]=dp[i-1][j-1]
                else:dp[i][j]=False
        return dp[m][n]
                
