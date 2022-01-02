#Time, space O(n*m)
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        
        dp =[[x for x in range(len(word1) + 1)] for y in range(len(word2) + 1)]
        
      #Updating first row and column  
        for i in range(len(word2)+1):
            dp[i][0]=i
        
        for j in range(len(word1)+1):
            dp[0][j]=j
    
    #Min of Add,replace,delete operations
        for i in range(1,len(word2)+1):
            for j in range(1,len(word1)+1):
                
                if word2[i-1]==word1[j-1]:
                    dp[i][j]=dp[i-1][j-1]  
                else:
                    dp[i][j]=min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j]) + 1
                
                
        return dp[len(word2)][len(word1)]
