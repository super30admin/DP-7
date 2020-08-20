-------------------------Regular Expression Matching-----------------------------------------
# Time Complexity : O(MXN) as M is the number of string and N is number of Pattern 
# Space Complexity : O(MXN) 
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# 
# We will iterate through string and patterna and check if the char in s and char in pattern is same or not. If yes, we will 
# take of the characters until previous character are true or not.  else of the pattern is '*' , then we will check of the char in s
# is same as prev character in p then we can take the 0 case and 1 case by taking 1 character of char before * from above. 
# If the both the conditions are not satisfied then we will take the keep the particular case as False. At last return the last element which is dp[-1][-1]

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if not s and not p:
            return True
        
        m = len(s)
        n = len(p)
        
        dp = [[False for i in range(n+1)] for j in range(m+1)]
        
        dp[0][0] = True
        for i in range(1, n+1):
            if p[i-1] == '*':
                dp[0][i] = dp[0][i-2]
                
        for i in range(1,m+1):
            for j in range(1,n+1):
                if s[i-1] == p[j-1] or p[j-1] == '.':
                    dp[i][j] = dp[i-1][j-1]
                elif p[j-1] == '*':
                    dp[i][j] = dp[i][j-2]
                    if p[j-2] == '.' or p[j-2] == s[i-1]:
                        dp[i][j] = dp[i][j] or dp[i-1][j]
                else:
                    dp[i][j] =  False
        return dp[-1][-1]