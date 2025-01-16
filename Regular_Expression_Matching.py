class Solution:
    # Approach:
    # Use dynamic programming to solve the problem. Create a 2D table where dp[i][j] represents whether
    # the first i characters of s match the first j characters of p. Handle '.' and '*' based on their
    # behavior, with careful handling of zero or more occurrences for '*'.

    def isMatch(self, s: str, p: str) -> bool:
        # Lengths of input string and pattern
        m, n = len(s), len(p)

        # Initialize a DP table with False values
        dp = [[False] * (n + 1) for _ in range(m + 1)]

        # Base case: Empty string matches empty pattern
        dp[0][0] = True

        # Handle patterns with '*' that can match an empty sequence
        for j in range(2, n + 1):
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 2]

        # Fill the DP table
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if p[j - 1] == '.' or p[j - 1] == s[i - 1]:
                    # If characters match or pattern has '.', carry forward the diagonal value
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == '*':
                    # If pattern has '*', there are two cases:
                    # 1. Ignore the '*' and preceding character (zero occurrence)
                    dp[i][j] = dp[i][j - 2]

                    # 2. Use the '*' to match one or more occurrences of the preceding character
                    # Check if preceding character matches current character in s
                    if p[j - 2] == '.' or p[j - 2] == s[i - 1]:
                        dp[i][j] |= dp[i - 1][j]

        # Return the value for the entire string and pattern
        return dp[m][n]

# Time Complexity: O(m * n) where m is the length of s and n is the length of p.
# Space Complexity: O(m * n) for the DP table.
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this: No
