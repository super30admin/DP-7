#time: O(m*n)
#space: O(m*n)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m=len(s)+1
        n=len(p)+1
        dp=[[False for _ in range(n)]for _ in range(m)]
        dp[0][0]= True
        for j in range(1,n):
            if p[j-1] == '*':
                dp[0][j]= dp[0][j-2]

        for i in range(1,m):
            for j in range(1,n):
                if p[j-1] == s[i-1]or  p[j-1] == '.':
                    dp[i][j]=dp[i-1][j-1]
                elif p[j-1] == '*':
                    dp[i][j] = dp[i][j-2] 
                    if p[j-2] == s[i-1] or p[j-2] =='.':
                        dp[i][j] = dp[i][j] or dp[i-1][j]
        return dp[m-1][n-1]
        
