#Time complexity : O(MN) where M is len(S) and N is len(P)
#Space complexity : O(MN) 
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s==p:
            return True
        
        len_s = len(s)
        len_p = len(p)
   
        dp = [[False]*(len_p+1) for i in range(len_s+1)] #fill dp matrix with False
        dp[0][0] = True
        
        for j in range(1,len(dp[0])):  #first row
            if p[j-1]=='*':
                dp[0][j] = dp[0][j-2]

        for i in range(1,len(dp)):
            for j in range(1,len(dp[0])):
                #not *
                if p[j-1] != '*':
                    if p[j-1]=='.' or p[j-1]==s[i-1]: #matching case 
                        dp[i][j] = dp[i-1][j-1]
                    
                
                #* check prev character match
                else:
                    dp[i][j]=dp[i][j-2]
                    if p[j-2] =='.' or p[j-2] == s[i-1]: #character match of previoius element
                        dp[i][j] = dp[i][j] or dp[i-1][j]

        return dp[len(dp)-1][len(dp[0])-1]