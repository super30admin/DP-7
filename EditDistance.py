# Time Complexity : O(MN)
# Space Complexity : O(MN)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach
# Using DP Approach


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:

        dp = [[0 for j in range(len(word1) + 1)]
              for j in range(len(word2) + 1)]
        dp[0][0] = 0
        #top row
        for j in range(len(word1) + 1):
            dp[0][j] = j

        #top col
        for i in range(len(word2) + 1):
            dp[i][0] = i

        for i in range(1, len(word2) + 1):
            for j in range(1, len(word1) + 1):
                if word1[j - 1] == word2[i - 1]:
                    dp[i][j] = dp[i - 1][j - 1]
                else:
                    dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j],
                                   dp[i][j - 1]) + 1

        return dp[-1][-1]
