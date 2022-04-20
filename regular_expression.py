# // Time Complexity :O(m*n)
# // Space Complexity :O(m*n)

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp=[[False for i in range(len(p)+1)] for j in range(len(s)+1)]
        for i in range(len(dp[0])):
            if i==0:
                dp[0][i]=True
            else:
                print(i)
                if p[i-1]=='*':
                    dp[0][i]=dp[0][i-2]
                    
        for i in range(1,len(dp)):
            for j in range(1,len(dp[0])):
                if p[j-1]!='*':
                    if s[i-1]==p[j-1] or p[j-1]=='.':
                        dp[i][j]=dp[i-1][j-1]
                else:
                    if s[i-1]==p[j-2] or p[j-2]=='.':
                        dp[i][j]=dp[i][j-2] or dp[i-1][j]
                    else:
                        dp[i][j]=dp[i][j-2] 
                        
        return dp[-1][-1]
        