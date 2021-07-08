class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if word1 is None or word2 is None: return -1

        dp = [[0 for _ in range(len(word2) + 1)] for _ in range(len(word2) + 1)]

        for row in range(len(dp)):
            dp[row][0] = row
        for row in  range(len(dp[0])):
            dp[0][0] = row


        for row in range(len(dp)):
            for column in  range(len(dp[0])):

                if word1[column - 1] == word2[row - 1]:
                    dp[row][column] = dp[row - 1][column - 1]
                else:
                    dp[row][column] = 1 + min(dp[row - 1][column - 1], min(dp[row][column - 1], dp[row - 1][column]))

        return dp[-1][-1]