#Regular Expression Matching

# // Time Complexity : O(N*M)
# // Space Complexity :O(N*M)
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        
        dp = [[False for i in range(len(p)+1)] for j in range(len(s)+1)]
        
        dp[0][0]=True
        for i in range(len(s)+1):
            for j in range(1,len(p)+1):
                if i==0:
                    if p[j-1]=='*':                    # if its '*' on the first row, then make it the same as 2 index before
                        dp[i][j] = dp[i][j-2]
                if s[i-1] ==p[j-1] or p[j-1]=='.':      #if its the same letter or a . , then make it what it was one letter up and one back
                    dp[i][j] = dp[i-1][j-1]
                if p[j-1]=='*':
                    if p[j-2]==s[i-1] or p[j-2]=='.':       #case 1, when it equals to the previous letter or a '.', then choose between either one up or two back
                        dp[i][j] = dp[i-1][j] or dp[i][j-2] 
                    else:
                        dp[i][j] =  dp[i][j-2]              #else, its two back
             
        return dp[-1][-1]
                        
        