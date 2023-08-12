class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        '''
        DP with 2D array
        Time complexity - O(len_s * len_p)
        Space complexity - O(len_s * len_p)
        '''
        # Get the lengths of the input strings
        len_s = len(s)
        len_p = len(p)

        # Create a 2D DP table to store matching results
        dp = [[False] * (len_p + 1) for _ in range(len_s + 1)]

        # Base case: Empty string and empty pattern match
        dp[0][0] = True

        # Fill in the first row for patterns like 'a*'
        for j in range(2, len_p + 1):
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 2]

        # Fill in the DP table using recurrence relation
        for i in range(1, len_s + 1):
            for j in range(1, len_p + 1):
                if p[j - 1] == s[i - 1] or p[j - 1] == '.':
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == '*':
                    dp[i][j] = dp[i][j -
                                     2] or (dp[i - 1][j] and (s[i - 1] == p[j - 2] or p[j - 2] == '.'))

        return dp[len_s][len_p]
