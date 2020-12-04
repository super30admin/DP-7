"""
Leetcode: https://leetcode.com/problems/regular-expression-matching/
Approch: Dynamic Programming: Bottom-Up Variation
Time Complexity: O(T*P) where T is length of the text, P is the length of the pattern.
Space Complexity: O(T*P), the only memory we use is the O(T*P) boolean entries in our cache.
"""


class Solution:
    def isMatch(self, text: str, pattern: str) -> bool:
        dp = [[False] * (len(pattern) + 1) for _ in range(len(text) + 1)]

        dp[-1][-1] = True
        for i in range(len(text), -1, -1):
            for j in range(len(pattern) - 1, -1, -1):
                first_match = i < len(text) and pattern[j] in {text[i], '.'}
                if j+1 < len(pattern) and pattern[j+1] == '*':
                    dp[i][j] = dp[i][j+2] or first_match and dp[i+1][j]
                else:
                    dp[i][j] = first_match and dp[i+1][j+1]

        return dp[0][0]