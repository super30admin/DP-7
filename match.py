
#Time Complexity : O(nm)
#Space Complexity : O(nm)
# Did this code successfully run on Leetcode : Yes
#Any problem you faced while coding this : No

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp = [[False]*(len(p)+1) for _ in range(len(s)+1)]
        dp[0][0] = True
        for i in range(1,len(p)+1):
            if p[i-1] == '*':
                dp[0][i] = dp[0][i-2]
        for i in range(1, len(dp)):
            dp[i][0] = False
            for j in range(1, len(dp[0])):
                if p[j-1] != '*':
                    if s[i-1] == p[j-1] or p[j-1] == '.':
                        dp[i][j] = dp[i-1][j-1]
                    else:
                        dp[i][j] = False
                else:
                    #zero case
                    dp[i][j] = dp[i][j-2]
                    #one case
                    if not dp[i][j]:
                        if (s[i-1] == p[j-2]) or (p[j-2] == '.'):
                            dp[i][j] = dp[i-1][j]     
      
        return dp[len(dp)-1][len(dp[0])-1]