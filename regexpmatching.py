# Using dynamic programming
# If the ch in both s and p index are the same, check if the substrings before it match (look at the left top diagonal value)
# If the ch at p is ".", it can match with any character. Check whether the substrings before it match.
# If the current char in p is "*", check for zero case (when the character before the * appears 0 times)
# also, check if the character before it in the pattern matches with the character in s or it was '.', then check for the one case. (value above the current row)
# Time complexity - O(nm)
# Space complexity - O(nm)
# Did this solution run on leetcode? - yes
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        
        n = len(s)
        m = len(p)
        
        dp = [[False for _ in range(m+1)] for _ in range(n+1)]
        
        # fill up the topmost row
        dp[0][0] = True
        for i in range(1, m+1):
            if p[i-1]=="*":
                dp[0][i] = dp[0][i-2]
        
        
        for i in range(1, n+1):
            for j in range(1, m+1):
                if s[i-1] == p[j-1] or p[j-1] == ".":
                    dp[i][j] = dp[i-1][j-1]
                elif p[j-1] == "*":
                    dp[i][j] = dp[i][j-2] # zero case
                    if (p[j-2] == s[i-1] or p[j-2] == ".") and dp[i-1][j]: # can take max over booleans
                        dp[i][j] = dp[i-1][j] # one case
                        
        return dp[n][m]