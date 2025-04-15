'''
    Time Complexity: O(m*n)
    Space Complexity: O(n)
'''
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)

        dp = [[False for _ in range(n+1)] for _ in range(2)]
        dp[0][0] = True 

        for j in range(1, n+1):
            if p[j-1] == "*":
                dp[0][j] = dp[0][j-2]

        for i in range(1, m+1):
            for j in range(1, n+1):
                dp[1][j] = False
                if p[j-1] == "*":
                    case0 = dp[1][j-2]
                    case1 = dp[0][j] if s[i-1] == p[j-2] or p[j-2] == "." else False
                    dp[1][j] = case0 or case1
                elif s[i-1] == p[j-1] or p[j-1] == ".":
                    dp[1][j] = dp[0][j-1]

            dp[0] = dp[1][:]

        return dp[1][n]

