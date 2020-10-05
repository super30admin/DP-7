# Time complexity : O(m*n)
# Space complexity : O(m*n)
# Works on leetcode : Yes
#Approach - We initialize dp array with all False except the first element. We then iterate through p and s. If p-1 is * we
# need dp[p-2][s] for dp[p][s], also later we modify dp[p][s] for propogation (or moving horizontal) by using dp[i][j-1] if 
# either p-2 == s-1 or p-2 is "."

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp = [[False]*(len(s)+1) for _ in range(len(p)+1)]
        #i is for p and j is for s lengths
        dp[0][0] = True
        
        #case when s is empty
        for i in range(2, len(p)+1):
            dp[i][0] = dp[i-2][0] and p[i-1]=="*"
            
        for i in range(1,len(p)+1):
            for j in range(1,len(s)+1):
                if p[i-1]!="*":
                    dp[i][j] = dp[i-1][j-1] and (p[i-1]==s[j-1] or p[i-1]==".")
                    
                else:
                    dp[i][j] |= dp[i-2][j] #or dp[i-1][j]
                    
                    if p[i-2]==s[j-1] or p[i-2]==".":
                        dp[i][j] |=  dp[i][j-1]
                        
        return dp[-1][-1]