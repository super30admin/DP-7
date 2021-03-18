class Solution:
  '''
  TIme, Space: O(n^2)
  '''
    def isMatch(self, s: str, p: str) -> bool:
        dp = [[False for i in range(0,len(p)+1)] for j in range(0,len(s)+1)]
        dp[0][0] = True
        
        for j in range(1,len(p)+1):
            if(p[j-1]=="*"):
                dp[0][j] = dp[0][j-2]
        
        
        for i in range(1, len(s)+1):
            for j in range(1,len(p)+1):
                if(p[j-1]=="*"):
                    # 0 case or 1 case
                    dp[i][j] = dp[i][j-2] 
                    if(dp[i][j] == False):
                        dp[i][j] = (s[i-1] == p[j-2] or p[j-2]==".") and dp[i-1][j]
                else:
                    if(p[j-1]==s[i-1] or p[j-1]==".") and (dp[i-1][j-1]==True):
                        dp[i][j] = True
        
        return dp[-1][-1]
