class Solution:
    # TC : O(m*n)
    # SC :O(m*n)
    def isMatch(self, s: str, p: str) -> bool:
        # dp[i][j] will be True if s[0..i-1] matches p[0..j-1]
        dp = [[False] * (len(p) + 1) for _ in range(len(s) + 1)]
        dp[0][0] = True  # Empty string matches empty pattern

        # Initialize dp for cases where pattern contains '*', which can match empty string
        for j in range(2, len(p) + 1):
            if p[j-1] == '*':
                dp[0][j] = dp[0][j-2]  # '*' matches zero occurrences of the preceding character

        # Fill the dp table
        for i in range(1, len(s) + 1):
            for j in range(1, len(p) + 1):
                if p[j-1] == s[i-1] or p[j-1] == '.':
                    dp[i][j] = dp[i-1][j-1]  # Current characters match, so inherit the result
                elif p[j-1] == '*':
                    # Two scenarios for '*' matching:
                    # 1. '*' matches zero occurrences of the previous character (i.e., ignore it)
                    dp[i][j] = dp[i][j-2]
                    # 2. '*' matches one or more occurrences of the previous character
                    if p[j-2] == s[i-1] or p[j-2] == '.':
                        dp[i][j] = dp[i][j] or dp[i-1][j]

        return dp[len(s)][len(p)]