class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word2)  # ros
        n = len(word1)  # horse

        if len(word1) > len(word2):
            return self.minDistance(word2, word1)

        dp = [0] * (n + 1)

        # filing the row
        for j in range(len(dp)):
            dp[j] = j

        for i in range(1, m + 1):
            temp = dp[0]
            for j in range(n + 1):
                temp2 = dp[j]
                if j == 0:
                    dp[j] = i
                else:
                    if word1[j - 1] == word2[i - 1]:
                        dp[j] = temp
                    else:
                        dp[j] = min(dp[j - 1], min(dp[j], temp)) + 1

                temp = temp2

        return dp[n]

# Dynamic Programming
# Time Complexity: O(m * n)
# Space Complexity: O(n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
