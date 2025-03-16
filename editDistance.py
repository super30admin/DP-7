class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        # T: O(m * n), S: O(n)
        m, n = len(word1), len(word2)
        prev = list(range(n + 1))  # Base case: converting empty string to word2

        for i in range(1, m + 1):
            curr = [i] + [0] * n  # First value corresponds to deleting all characters
            for j in range(1, n + 1):
                if word1[i - 1] == word2[j - 1]:
                    curr[j] = prev[j - 1]
                else:
                    curr[j] = 1 + min(prev[j], curr[j - 1], prev[j - 1])
            prev = curr  # Move to the next row

        return prev[n]
