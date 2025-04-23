# Time Complexity: O(m * n), where m = len(text), n = len(pattern)
# Space Complexity: O(m * n) for the DP table

class Solution:
    def isMatch(self, text: str, pattern: str) -> bool:
        if text == pattern or pattern == '*':
            return True

        m, n = len(text), len(pattern)
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True

        # Fill the first row (pattern vs empty text)
        for j in range(1, n + 1):
            if pattern[j - 1] == '*':
                dp[0][j] = dp[0][j - 2]

        # Fill the remaining dp matrix
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if pattern[j - 1] != '*':
                    if pattern[j - 1] == text[i - 1] or pattern[j - 1] == '.':
                        dp[i][j] = dp[i - 1][j - 1]
                else:
                    # 0 occurrence of previous char
                    dp[i][j] = dp[i][j - 2]
                    # 1+ occurrence if char before '*' matches current text
                    if pattern[j - 2] == text[i - 1] or pattern[j - 2] == '.':
                        dp[i][j] = dp[i][j] or dp[i - 1][j]

        return dp[m][n]
