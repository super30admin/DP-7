"""
Time Complexity : O(m*n)
Space Complexity : O(m*n)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Explaination
use 2D array (Boolean)
1. if characters are matching : take from diagonal up
2. not matching then : False
3. if *:  (
    0: two col back in same row - we do not select the char i.e. zero time
    OR
    1. if preceding char  == current char: we take from above: we select the char
)

"""

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)
        dp = [[False]*(n+1) for i in range(m+1)]
        dp[0][0] = True
        
        for i in range(1, n+1):
            if p[i-1] == "*":
                dp[0][i] = dp[0][i-2]

        for i in range(1, m+1):
            for j in range(1,n+1):
                # if char at pattern p
                if p[j-1] != "*":
                    if s[i-1] == p[j-1] or p[j-1] == ".":
                        dp[i][j] = dp[i-1][j-1]
                #if * at pattern p
                else:
                    # Zero taken
                    dp[i][j] = dp[i][j-2]
                    
                    # One taken iff preceding char of pattern p is 
                    # matching with current char of String s
                    if p[j-2] == s[i-1] or p[j-2] == ".":
                        if dp[i-1][j]:
                            dp[i][j] = True
                            
        
        return dp[m][n]
        