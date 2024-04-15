'''
Time Complexity - O(m*n). We are traversing through a matrix
Space Complexity - O(m*n). We have a DP matrix. O(n) space optimized.

Works on Leetcode
'''
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m=len(s)
        n = len(p)
        dp = [[False for j in range(n+1)] for i in range(m+1)]
        #consider the example as s="aab" and p = "c*a*b"
        # - = -
        dp[0][0] = True

        for j in range(1, n+1):
            # - -> -c, -c*, -c*a, -c*a*, -c*a*b
            if p[j-1] == "*":
                dp[0][j] = dp[0][j-2]
        for i in range(1, m+1):
            for j in range(1, n+1):
                #when current character is an alphabet in pattern
                if p[j-1] != "*":
                    #when the characters match we take the result until the previous characters that is diagonally left up
                    if p[j-1] == s[i-1] or p[j-1] == ".":
                        dp[i][j] = dp[i-1][j-1]
                else:
                    #when the current character is a * in pattern
                    if p[j-2] == s[i-1] or p[j-2] == ".":
                        #when the character before * matches the character in sample string 
                        #we take an or of the diagonally up left and two characters before current character in pattern
                        dp[i][j] = dp[i-1][j] or dp[i][j-2]
                    else:
                        #else we take the result upto the character before * and character before it.
                        dp[i][j] = dp[i][j-2]
        return dp[m][n]

        #Space Optimized
        #prev stores the row above as we only need that
        prev = [False for j in range(0,n+1)]
        prev[0] = True
        for j in range(1, n+1):
            if p[j-1] == "*":
                prev[j] = prev[j-2]
        for i in range(1, m+1):
            #curr will store the current row
            curr = [False for k in range(0, n+1)]
            for j in range(1, n+1):
                if p[j-1] != "*":
                    if p[j-1] == s[i-1] or p[j-1] == ".":
                        curr[j] = prev[j-1]
                else:
                    if p[j-2] == s[i-1] or p[j-2] == ".":
                        curr[j] = prev[j] or curr[j-2]
                    else:
                        curr[j] = curr[j-2]
            #at the end the current row becomes previous row for next iteration
            prev = curr
        return prev[n]