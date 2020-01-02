'''

Leetcode- 10. Regular Expression Matching - https://leetcode.com/problems/regular-expression-matching/
Time complexity - O(M)(N)
space complexity - O(M)(N)
Approach - DP
          1) when chars are equal - check dp[i-1][j-1] - previous pattern should match
          2) when there is a "." - which is technically equal to case1
          3) when there is a "*" - we have to check dp[i-2][j] or if previous char of star are equal then we compare the dp[i-2]              [j-2] and dp[i-1][j-1] (what the previous * gives)
          
          
'''
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        n=len(s)+1
        m=len(p)+1
        dp =[[False for _ in range(m)] for _ in range(n)]
        
        #fill initial column
        
        dp[0][0]=True
        
        #initial row
        for j in range(1,m):
            if p[j-1]=='*':
                dp[0][j]=dp[0][j-2]
                
        for i in range(1,n):
            for j in range(1,m):
                #case1 and case2
                if p[j-1]=='.' or p[j-1]==s[i-1]:
                    dp[i][j]=dp[i-1][j-1]

                    
                #case3 
                if p[j-1]=='*':
                    dp[i][j]=dp[i][j-2]
                    if p[j-2]=="." or p[j-2] ==s[i-1]:
                            dp[i][j]= dp[i][j-2] or dp[i-1][j]
                            
        return dp[-1][-1]
                        
        