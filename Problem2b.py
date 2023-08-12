class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        '''
        DP with 1D array
        Time complexity - O(len_s * len_p)
        Space complexity - O( len_p)
        '''
        len_s = len(s)
        len_p = len(p)

        # Initialize a 1D DP array to store matching status of substrings
        dp = [False] * (len_p + 1)
        dp[0] = True  # Empty pattern matches empty string

        for j in range(2, len_p + 1):
            if p[j - 1] == '*':
                dp[j] = dp[j - 2]

        for i in range(1, len_s + 1):
            prev = dp[0]
            dp[0] = False
            for j in range(1, len_p + 1):
                temp = dp[j]  # Store the current dp[j]
                if p[j - 1] == s[i - 1] or p[j - 1] == '.':
                    dp[j] = prev
                elif p[j - 1] == '*':
                    dp[j] = dp[j - 2] or (dp[j] and (s[i - 1]
                                          == p[j - 2] or p[j - 2] == '.'))
                else:
                    dp[j] = False
                prev = temp  # Update prev for the next iteration

        return dp[len_p]
