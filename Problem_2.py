class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sl, pl = len(s), len(p)
        dp = [ [False for j in range(pl+1)] for i in range(sl+1)]
        dp[0][0] = True
        for j in range(1, len(dp[0])):
            if p[j-1] == '*':
                dp[0][j] = dp[0][j-2]
                
        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                # not a *
                if p[j-1] != '*':
                    if p[j-1] == s[i-1] or p[j-1] == '.':
                        dp[i][j] = dp[i-1][j-1]    
                else:
                    # 0 case
                    dp[i][j] = dp[i][j-2]
                    # 1 case
                    if p[j-2] == s[i-1] or p[j-2] == '.':
                        dp[i][j] = dp[i][j] or dp[i-1][j]
                        
        return dp[sl][pl]
                    

# Time Complexity: O(sl * pl)
# Space Complexity: O(sl * pl)