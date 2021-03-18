# Time Complexity : O(MN)
# Space Complexity : O(MN)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sLength = len(s)
        pLength = len(p)
        
        dp = [[0 for i in range(pLength+1)] for i in range(sLength+1)]
        
        dp[0][0] = True
        
        for i in range(1, pLength+1):
            if p[i-1] == "*":
                dp[0][i] = dp[0][i-2]
        
        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                if p[j-1] == s[i-1] or p[j-1] == '.':
                    dp[i][j] = dp[i-1][j-1]
                elif p[j-1] == '*':
                    dp[i][j] = dp[i][j-2]
                    if p[j-2] == '.' or p[j-2] == s[i-1]:
                        if dp[i][j-2] or dp[i-1][j]:
                            dp[i][j] = True
                            
        return dp[-1][-1]