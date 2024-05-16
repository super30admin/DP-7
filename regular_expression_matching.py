#Approach 1:
# Time Complexity : O(m*n)
# Space Complexity : O(m*n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p:
            return True
        
        m = len(s)
        n = len(p)
        dp = [[False]*(n+1) for _ in range(m+1)]

        dp[0][0] = True

        #first row
        for j in range(1, n+1):
            c = p[j-1]
            if c == '*':
                #zero case
                dp[0][j] = dp[0][j-2]
        
        #rest of matrix
        for i in range(1, m+1):
            for j in range(1, n+1):
                pchar = p[j-1]
                if pchar != '*':
                    if pchar == '.' or pchar == s[i-1]:
                        #diaUp
                        dp[i][j] = dp[i-1][j-1]
                else:
                    # if it's '*'
                    if(s[i-1] == p[j-2] or p[j-2] == '.'):
                        #0 and 1 case
                        dp[i][j] = dp[i-1][j] or dp[i][j-2]
                    else:
                        #0 case
                        dp[i][j] = dp[i][j-2]
        
        return dp[m][n]

#Approach 2: Optimized on space
# Time Complexity : O(m*n)
# Space Complexity : O(n)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p:
            return True
        
        m = len(s)
        n = len(p)
        dp = [False]*(n+1)
        dp[0] = True
        
        for j in range(1, n+1):
            c = p[j-1]
            if c == '*':
                dp[j] = dp[j-2]
        
        for i in range(1, m+1):
            diagUp = dp[0]
            for j in range(0, n+1):
                temp = dp[j]
                if j == 0:
                    dp[j] = False
                    continue
                pchar = p[j-1]
                if pchar != '*':
                    if pchar == '.' or pchar == s[i-1]:
                        dp[j] = diagUp
                    else:
                        dp[j] = False
                else:
                    if s[i-1] == p[j-2] or p[j-2] == '.':
                        dp[j] = dp[j] or dp[j-2]
                    else:
                        dp[j] = dp[j-2]
                diagUp = temp

        return dp[n]
