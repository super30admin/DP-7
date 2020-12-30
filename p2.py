#Time: O(mn)
#Space: O(mn)
class Solution:
    def isMatch(self, s, p):
        dp = [[False for _ in range(len(p)+1)] for _ in range(len(s)+1)]
        dp[0][0] = True

        for i in range(1,len(dp[0])):
            if i >1 and p[i-1] == '*':
                dp[0][i] = dp[0][i-2]

        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                if p[j-1] != '*':
                    if s[i-1] == p[j-1] or p[j-1] == '.':
                        dp[i][j] = dp[i-1][j-1]
                else:
                     
                    dp[i][j] = dp[i][j-2]

                    if p[j-2] == '.' or s[i-1] == p[j-2]:
                        if dp[i-1][j]:
                            dp[i][j] = dp[i-1][j] 

        return dp[-1][-1]
