# Time Complexity : O(nm)
# Space Complexity : O(nm)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : Yes

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:

        w1 = len(word1)
        w2 = len(word2)
        # array to store the convertion history
        dp = [[0] * (w1 + 1) for _ in range(w2 + 1)]

        for i in range(len(dp[0])):
            dp[0][i] = i

        for i in range(1, len(dp)):
            dp[i][0] = i

            for j in range(1, len(dp[0])):
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]

                else:
                    dp[i][j] = 1 + min(dp[i][j-1], dp[i-1][j-1], dp[i-1][j])

        return dp[w2][w1]
