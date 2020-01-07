'''
Accepted on leetcode(72)
time - O(m*n)
space - O(m*n)
Approach(Dynamic programming):
1. create a dp matrix
2. Fill it considering 2 conditions: i) if chars are equal in 2 words. ii) if chars are not equal in 2 words.
3. return the last element in dp matrix.
'''


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        n = len(word2) + 1
        m = len(word1) + 1
        dp = [[0 for i in range(len(word1) + 1)] for i in range(len(word2) + 1)]

        # fill initial column
        for i in range(1, n):
            dp[i][0] = i

        # fill initial row
        for j in range(1, m):
            dp[0][j] = j

        for i in range(1, n):
            for j in range(1, m):
                # if chars are equal.
                if word1[j - 1] == word2[i - 1]:
                    dp[i][j] = dp[i - 1][j - 1]
                # if chars are not equal.
                else:
                    dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1

        return dp[n - 1][m - 1]


