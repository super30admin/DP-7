#  https://leetcode.com/problems/regular-expression-matching/

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)
        dp = [[False]* (n+1) for _ in range(m+1)]

        dp[0][0] = True
        for j in range(1,n+1):
        # only 0 case for first row - 0 cased is taking nothing instead of star
            if p[j-1] == "*":
                dp[0][j] = dp[0][j-2]
        
        for i in range(1,m+1):
            for j in range(1,n+1):
                if p[j-1] != "*":

                    if s[i-1] == p[j-1] or p[j-1] == ".":
                        dp[i][j] = dp[i-1][j-1]
                    
                    else:
                        dp[i][j] = False
                
                else:
                    # 1 case
                    if s[i-1] == p[j-2] or p[j-2] == ".":
                        dp[i][j] = dp[i-1][j] or dp[i][j-2]

                    # 0 case
                    else:
                        dp[i][j] = dp[i][j-2]
        
        return dp[m][n]

        
# TC: O(m*n)
# SC: O(m*n)




#--------------------------
# using 1d list

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)
        #dp = [[False]* (n+1) for _ in range(m+1)]
        dp = [False] * ( n+1)
        dp[0] = True
        for j in range(1,n+1):
        # only 0 case for first row - 0 cased is taking nothing instead of star
            if p[j-1] == "*":
                dp[j] = dp[j-2]
        
        for i in range(1,m+1):
            diagUp = dp[0]
            dp[0] = False
            for j in range(1,n+1):
                temp = dp[j]

                if p[j-1] != "*":
                    if s[i-1] == p[j-1] or p[j-1] == ".":
                        dp[j] = diagUp
                    
                    else:
                        dp[j] = False
                
                else:
                    # 1 case
                    if s[i-1] == p[j-2] or p[j-2] == ".":
                        dp[j] = dp[j] or dp[j-2]

                    # 0 case
                    else:
                        dp[j] = dp[j-2]
                diagUp = temp
        
        return dp[n]

        
# TC: O(n)
# SC: O(n)