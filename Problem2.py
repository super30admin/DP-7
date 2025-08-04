"""
Time Complexity: O(mn)
Space Complexity: O(n)
"""
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        dp = [False] * (n + 1)
        dp[0] = True

        for j in range(1, n + 1):
            if p[j - 1] == '*':
                dp[j] = dp[j - 2]

        for i in range(1, m + 1):
            diagUp = dp[0]
            dp[0] = False
            for j in range(1, n + 1):
                temp = dp[j]
                if p[j - 1] == '*':
                    if p[j - 2] == s[i - 1] or p[j - 2] == '.':
                        dp[j] = dp[j] or dp[j - 2]
                    else:
                        dp[j] = dp[j - 2]
                else:
                    if p[j - 1] == s[i - 1] or p[j - 1] == '.':
                        dp[j] = diagUp
                    else:
                        dp[j] = False
                diagUp = temp

        return dp[n]
