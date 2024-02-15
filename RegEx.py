#https://leetcode.com/problems/regular-expression-matching/
#TC: O(m*n)
#SC: O(m*n)


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m=len(s)+1
        n=len(p)+1
        dp=[[False]*n for _ in range (m)]
        
        dp[0][0]=True
        for j in range(1,n):
            if p[j-1]=="*":
                dp[0][j]=dp[0][j-2]
        
        for i in range(1,m):
            for j in range(1,n):
                if s[i-1]==p[j-1] or p[j-1]==".":
                    dp[i][j]=dp[i-1][j-1]
                if p[j-1]=="*":
                    #0Case:
                    if p[j-2]==s[i-1] or p[j-2]==".":
                        dp[i][j]=(dp[i][j-2] or dp[i-1][j])
                    else:
                        dp[i][j]=dp[i][j-2]
        print(dp)
        return dp[-1][-1]