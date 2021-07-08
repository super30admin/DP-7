class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s is None or p is None: return True
        dp = [[ False for _ in range(len(p)+1)] for _ in range(len(s)+1)]
        dp[0][0] = True
        for j in range(1,len(dp[0])):
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 2]
        for row in range(1, len(dp)):
            for column in range(1, len(dp[0])):
                if p[column - 1] != '*':
                      if p[column - 1] == s[row - 1] or p[column - 1] == '.':

                            dp[row][column] = dp[row - 1][column - 1]
                else:
            # zero case
                    dp[row][column] = dp[row][column - 2]

                    if p[column - 2] == s[row - 1] or p[column - 2] == '.':
                          dp[row][column] = dp[row][column] or dp[row - 1][column]

        return dp[-1][-1]