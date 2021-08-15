class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        """
        TC:O(m*n)
        SC:O(m*n)
        """
        if s==p: return True
        sl=len(s)
        pl=len(p)
        dp = [[False for i in range(pl+1)] for j in range(sl+1)]
        # top row
        dp[0][0]=True
        # iterating over all col
        for j in range(1, len(dp[0])):
            if p[j-1]=='*':
                dp[0][j]=dp[0][j-2]
        print(dp)
        
        for i in range(1,len(dp)):
            for j in range(1, len(dp[0])):
                if p[j-1]!='*':
                    if (p[j-1]==s[i-1]) or p[j-1]=='.':
                        dp[i][j]=dp[i-1][j-1]
                else:
                    # 0 and 1 case
                    dp[i][j]=dp[i][j-2]
                    # available 1 case
                    if p[j-2]==s[i-1] or p[j-2]=='.':
                        dp[i][j]=dp[i][j] or dp[i-1][j]
        return dp[sl][pl]
                        
                    
         