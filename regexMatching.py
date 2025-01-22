# T:O(m*n)
# S:O(m*n) for matrix O(n) for array
# Leetcode: Yes
# Issues: initialization


# dp array 7ms
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)

        dp = [False]*(n+1)

        dp[0] = True           

        for j in range(1,n+1):          # initialization 1
            pchar = p[j-1]
            if pchar == '*':
                dp[j] = dp[j-2]

            
        for i in range(1,m+1):
            diagUP = dp[0]
            dp[0] = False
            for j in range(1,n+1):
                temp = dp[j]
                pchar = p[j-1]
                if pchar == "*":
                    if s[i-1] == p[j-2] or p[j-2] == '.':
                        dp[j] = dp[j-2] or dp[j]
                    else:
                        dp[j] = dp[j-2]
                else:
                    if pchar == s[i-1] or pchar == '.':
                        dp[j] = diagUP
                    else:
                        dp[j] = False
                diagUP = temp
        return dp[n]
    

# dp matrix
class Solution: # redo thios one
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)

        dp = [[False]*(n+1) for _ in range(m+1)]
        dp[0][0] = True

        for j in range(1,n+1):                  #initialization
            pChar = p[j-1]
            if pChar == "*":
                dp[0][j] = dp[0][j-2]


        for i in range(1,m+1):
            for j in range(1,n+1):
                pChar = p[j-1]
                if pChar == "*":
                    if s[i-1] == p[j-2] or p[j-2] == '.':           # * + match
                        dp[i][j] = dp[i][j-2] or dp[i-1][j]
                    else:
                        dp[i][j] = dp[i][j-2]                       # * + no match

                else:
                    if pChar == s[i-1] or pChar == ".":             # regular
                        dp[i][j] = dp[i-1][j-1]
                    else:
                        dp[i][j] = False                            # no match
        return dp[m][n]