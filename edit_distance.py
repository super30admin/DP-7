
##Time complexity : O(MN)
#Space complexity : O(N)  #N is len of smaller word
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if word1 == word2:
            return 0
        m = len(word2) #lesser word rows
        n = len(word1) 
        if n<m:
            return self.minDistance(word2,word1)
        dp = [[0] for i in range(n+1)]
    
        for j in range(0,n+1):  
            dp[j] = j  #first row

        for i in range(1, m+1):
            temp = dp[0]
            dp[0] = i
            for j in range(1, n+1):
                temp2  = dp[j]
                if word1[j-1]==word2[i-1]:
                    dp[j] = temp
                else:
                    dp[j] = min(dp[j-1],temp2,temp)+1
                temp = temp2
  
        return dp[n]
    