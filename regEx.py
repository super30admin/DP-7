class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp=[False]*(len(p)+1)
        dp[0]=True

        #Initial
        for i in range(len(p)):
            if p[i]=='*':
                dp[i+1]=dp[i-1]
        
        #print(dp)

        for i in range(len(s)):
            diagUp=dp[0]
            dp[0]=False
            
            for j in range(len(p)):
                if s[i]==p[j] or p[j]=='.':
                    #print('. occured, so diagup is:', diagUp)
                    dp[j+1], diagUp= diagUp, dp[j+1]
                    #print('dp[j+1]:', dp)
                    continue
                
                if p[j]=='*':
                    old = dp[j + 1] 
                    zero_occ = dp[j - 1]  
                    more_occ = dp[j + 1] and (s[i] == p[j - 1] or p[j - 1] == '.')
                    dp[j + 1] = zero_occ or more_occ
                    diagUp = old
                
                elif s[i]!=p[j]:
                    diagUp=dp[j+1]
                    dp[j+1]=False
            #print(dp)

        return dp[-1]