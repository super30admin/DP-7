'''
    Time Complexity: O(m*n)
    Space Complexity: O(n)
'''
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)
        n = len(word2)

        dp = [[0 for _ in range(n+1)] for _ in range(2)]

        for i in range(1, n+1):
            dp[0][i] = i

        for i in range(1, m+1):
            dp[1][0] = i
            for j in range(1, n+1):
                if word1[i-1] == word2[j-1]:
                    dp[1][j] = dp[0][j-1]
                else:
                    delete = dp[1][j-1]
                    edit = dp[0][j-1]
                    add = dp[0][j]

                    res = min(min(delete, edit), add)
                    dp[1][j] = res + 1

            dp[0] = dp[1][:]

        return dp[0][n]