'''
Use DP
Time: O(sp)
Space: O(sp)
'''


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        
        sl = len(s)
        pl = len(p)
        dp = [[False for _ in range(pl+1)] for _ in range(sl+1)]
        
        dp[0][0] = True
        
        # top row
        for i in range(1, len(dp[0])):
            if p[i-1] == '*':
                dp[0][i] = dp[0][i-2]
            
        # other rows
        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                
                if p[j-1] != '*':
                    if p[j-1] == s[i-1] or p[j-1] == '.':
                        dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = dp[i][j-2]
                    
                    if s[i-1] == p[j-2] or p[j-2] == '.':
                        dp[i][j] = dp[i][j] or dp[i-1][j]
                    
                    
                
        
        return dp[len(dp)-1][len(dp[0])-1]


'''
Use DP
Time: O(sp)
Space: O(p)
'''

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        
        sl = len(s)
        pl = len(p)
        dp = [False for _ in range(pl+1)]
        
        
        dp[0] = True
        
        # top row
        for i in range(1, len(dp)):
            if p[i-1] == '*':
                dp[i] = dp[i-2]
            
        # other rows
        for i in range(1, sl+1):
            diag = dp[0]
            for j in range(0, pl+1):
                
                temp = dp[j]
                if j == 0:
                    dp[j] = False
                    continue
                
                
                if p[j-1] != '*':
                    if p[j-1] == s[i-1] or p[j-1] == '.':
                        dp[j] = diag
                    else:
                        dp[j] = False
                else:
                    dp[j] = dp[j-2]
                    
                    if s[i-1] == p[j-2] or p[j-2] == '.':
                        dp[j] = dp[j] or temp
                diag = temp

        return dp[len(dp)-1]