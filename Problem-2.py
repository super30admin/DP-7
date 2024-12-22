#Approach
# For every string and ? wave only if it matches the True else false. For . we have two aoptions we have choose any number of times
# if we have choosen it will match with current element [i][j-1] or [i-1][j] no choose case


#Complexities
#Time O(n*m)
#Space O(n*m)


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)
        dp = [[False for _ in range(n + 1)] for _ in range(m + 1)]

        dp[0][0] = True
        for j in range(1, n + 1):
            if p[j - 1] == "*":
                dp[0][j] = dp[0][j - 2]

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                pChar = p[j - 1]
                if pChar != "*":
                    if (s[i - 1] == pChar) or (pChar == "."):
                        dp[i][j] = dp[i - 1][j - 1]
                    else:
                        dp[i][j] = False

                else:
                    if (p[j - 2] == s[i - 1]) or (p[j - 2] == "."):
                        noChoose = dp[i][j - 2]
                        choose = dp[i - 1][j]
                        dp[i][j] = noChoose or choose
                    else:
                        dp[i][j] = dp[i][j - 2]

        return dp[m][n]

