"""
Time Complexity : O(m*n) where m is the length of s and n is the length of p
Space Complexity : O(m*n) where m is the length of s and n is the length of p
"""


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        s1 = len(s)
        p1 = len(p)
        dp = [[False for _ in range(p1+1)] for _ in range(s1+1)]

        dp[0][0] = True

        # iterate on the first row
        for j in range(1, p1+1):
            if p[j-1] == "*":
                dp[0][j] = dp[0][j-2]

        for i in range(1, s1+1):
            for j in range(1, p1+1):
                # normal character
                if p[j-1] != "*":
                    if p[j-1] == s[i-1] or p[j-1] == ".":
                        dp[i][j] = dp[i-1][j-1]
                else:
                    # if charater is *
                    # zero case
                    dp[i][j] = dp[i][j-2]

                    # one case
                    if p[j-2] == s[i-1] or p[j-2] == ".":
                        dp[i][j] = dp[i][j] or dp[i-1][j]

        return dp[s1][p1]
