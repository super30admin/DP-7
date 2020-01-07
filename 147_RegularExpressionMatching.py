'''
Accepted on leetcode(10)
Time - O(m*n)
space - O(m*n)

'''

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        n = len(s) + 1
        m = len(p) + 1

        dp = [[False for i in range(m)] for i in range(n)]

        # fill initial column/cell
        dp[0][0] = True

        # fill initial row
        for j in range(1, m):
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 2]

        # fill rest of dp matrix
        for i in range(1, n):
            for j in range(1, m):
                # case 1 and 2 - same chars or pattern has '.'
                if p[j - 1] == '.' or p[j - 1] == s[i - 1]:
                    dp[i][j] = dp[i - 1][j - 1]
                # case 3
                if p[j - 1] == '*':
                    # initially assign the value which is in '-2' columns in the same row.
                    dp[i][j] = dp[i][j - 2]
                    # then check if the value at -2 column in same row is a '.' or it is same as char at string at that position.
                    if p[j - 2] == '.' or p[j - 2] == s[i - 1]:
                        # assign true if either of them is true.
                        dp[i][j] = dp[i - 1][j] or dp[i][j - 2]

        return dp[n - 1][m - 1]