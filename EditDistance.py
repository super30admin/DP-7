# Time Complexity : O(m*n)
# Space Complexity : O(min(m,n))
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if word1 == word2:
            return 0
        
        m, n = len(word1), len(word2)

        # Swap to ensure the first string is always the shorter one
        if m < n:
            return self.minDistance(word2, word1)

        prev = list(range(n + 1))
        curr = [0] * (n + 1)

        for i in range(1, m + 1):
            curr[0] = i
            for j in range(1, n + 1):
                if word1[i - 1] == word2[j - 1]:
                    curr[j] = prev[j - 1]
                else:
                    curr[j] = min(prev[j - 1], prev[j], curr[j - 1]) + 1
            # Swap the arrays
            prev, curr = curr, prev

        return prev[n]

# Examples

# Example 1
solution = Solution()
result = solution.minDistance("horse", "ros")
print(result)  # Output: 3

# Example 2
solution = Solution()
result = solution.minDistance("intention", "execution")
print(result)  # Output: 5

# Example 3
solution = Solution()
result = solution.minDistance("abc", "yabd")
print(result)  # Output: 2