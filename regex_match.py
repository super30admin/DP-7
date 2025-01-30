"""
TC: O(m*n) m- len(string), n: len(pattern)
SP: O(n) 
on '*' there are teo choices eirther choose prev cahracter or dont choose.
"""
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp = [False] * (len(p) + 1)
        dp[0] = True
        for j in range(1, len(p) + 1):
            if p[j - 1] == "*":
                dp[j] = dp[j - 2]
        for i in range(1, len(s) + 1):
            diagUp = dp[0]
            dp[0] = False
            for j in range(1, len(p) + 1):
                temp = dp[j]
                if s[i - 1] == p[j - 1] or p[j - 1] == ".":
                    dp[j] = diagUp
                elif p[j - 1] == "*":
                    if s[i - 1] == p[j - 2] or p[j - 2] == ".":
                        dp[j] = dp[j - 2] or dp[j]
                    else:
                        dp[j] = dp[j - 2]
                else:
                    dp[j] = False
                diagUp = temp

        return dp[-1]
