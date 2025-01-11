# The minDistance method calculates the minimum number of operations (insert, delete, replace) 
# to convert word1 into word2.

# Dynamic Programming Approach:
# - Use a 1D `dp` array to save space, representing the cost for transforming suffixes of word2 into word1.
# - Iterate from the last character of both words backward.
# - Update the DP array based on:
#   - If characters match, carry forward the previous diagonal value.
#   - If characters differ, take the minimum cost of insert, delete, or replace operations.

# TC: O(m * n) - Process each character pair once.
# SC: O(n) - Space for the DP array.


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word1), len(word2)
        if m < n:
            m, n = n, m
            word1, word2 = word2, word1
        
        dp = [n - i for i in range(n + 1)]

        for i in range(m - 1, -1, -1):
            nextDp = dp[n]
            dp[n] = m - i
            for j in range(n - 1, -1, -1):
                temp = dp[j]
                if word1[i] == word2[j]:
                    dp[j] = nextDp
                else:
                    dp[j] = 1 + min(dp[j], dp[j + 1], nextDp)
                nextDp = temp
        return dp[0]