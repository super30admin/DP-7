"""
Time Complexity : O(m x n) where m and n are the length of strings p and s respectively
Space Complexity : O(m x n) where m and n are the length of strings p and s respectively
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
"""
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p: return True
        m = len(s)
        n = len(p)
        dp = [[False]*(n+1) for _ in range(m+1)]
        
        dp[0][0] = True
        # Marking first rows where we find '*' as two elements backward value
        for j in range(1, len(dp[0])):
            if p[j - 1] == "*":
                dp[0][j] = dp[0][j - 2]
        # Traverse through the rows and cols and marking all the ekements as True if 
        # they match. If there is a '.' we can generate any character so we look at 
        # the value just across the current position. If we encounter '*' we simply go
        # two step back for the zero case and for 1 case we if both the characters are
        # matching or it's a '.' we take the Truth value just above it
        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                if p[j - 1] != "*":
                    if p[j - 1] == s[i - 1] or p[j - 1] == ".":
                        dp[i][j] = dp[i - 1][j - 1]
                else:
                    
                    # Zero case
                    dp[i][j] = dp[i][j - 2]
                    
                    # One case
                    if p[j - 2] == s[i - 1] or p[j - 2] == ".":
                        dp[i][j] = dp[i][j] or dp[i - 1][j]
        return dp[m][n]