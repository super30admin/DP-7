# Time Complexity : O(m*n)
# Space Complexity : O(m*n)

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp = [[False for x in range(len(p)+1)] for y in range(len(s)+1)]
        dp[0][0] = True
        for j in range(1, len(dp[0])):
            if p[j-1] == '*':
                dp[0][j] = dp[0][j-2]

        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                if p[j-1] != '*':
                    if p[j-1] == s[i-1] or p[j-1] == '.':
                        dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = dp[i][j-2]
                    if p[j-2] == s[i-1] or p[j-2] == '.':
                        dp[i][j] = dp[i][j] or dp[i-1][j]

        for val in dp:
            print(val)
        return dp[-1][-1]
