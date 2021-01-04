#Time Complexity : O(n*m) where n and m are length of the two input words
#Space Complexity : O(n*m) where n and m are length of the two input words
#Did this code successfully run on Leetcode : Yes

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sLen, pLen = len(s), len(p)

        dp = [[False for _ in range(pLen+1)] for _ in range(sLen+1)]

        dp[0][0] = True
        for i in range(1, pLen+1):
            if p[i-1] == "*":
                dp[0][i] = dp[0][i-2]

        for i in range(1, sLen+1):
            for j in range(1, pLen+1):
                #normal character
                if p[j-1] != "*":
                    if p[j-1] == s[i-1] or p[j-1] == ".":
                        dp[i][j] = dp[i-1][j-1]
                # character is *
                else:
                    dp[i][j] = dp[i][j-2] #zero case
                    if p[j-2] == s[i-1] or p[j-2] == ".":
                        if dp[i-1][j]:
                            dp[i][j] = True

        return dp[sLen][pLen]
