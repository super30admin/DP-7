
"""
TC: O(m*n) m- len(string1), n: len(string2)
SP: O(n) 
add = (i+1, j), delete (i, j+1), edit(i+1, j+1)//diagup
"""
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word1)
        n = len(word2)
        dp = [0] * (n + 1)
        for j in range(1, n + 1):
            dp[j] = j
        for i in range(m):
            diagup = dp[0]
            dp[0] = i + 1
            for j in range(1, n + 1):
                temp = dp[j]
                if word1[i] == word2[j - 1]:
                    dp[j] = diagup
                else:
                    dp[j] = min(dp[j], dp[j - 1], diagup) + 1
                diagup = temp
        return dp[-1]
