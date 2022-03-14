'''
TC: O(m*n)
SC: O(m*n)
'''
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        slen = len(s)
        plen = len(p)
        dp = [[False for j in range(plen + 1)] for i in range(slen + 1)]
        
        dp[0][0] = True
        
        for i in range(1, plen + 1):
            if p[i - 1] == "*":
                dp[0][i] = dp[0][i - 2]
                
        for i in range(slen + 1):
            for j in range(1, plen + 1):
                if p[j - 1] != "*":
                    if (s[i - 1] == p[j - 1]) or p[j - 1] == ".":
                        dp[i][j] = dp[i - 1][j - 1]
                else:
                    # not choosing "_*" eg. "a*"
                    dp[i][j] = dp[i][j - 2]
                    # choosing "_*_" eg. "a*a"
                    if (s[i - 1] == p[j - 2]) or p[j - 2] == ".":
                        dp[i][j] = dp[i][j] or dp[i - 1][j]
        
        return dp[-1][-1]
                
        
        