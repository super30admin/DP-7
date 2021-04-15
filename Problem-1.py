#Time Complexity :o(mn)
#Space Complexity :o(mn)
#Did this code successfully run on Leetcode :yes
#Any problem you faced while coding this :no
class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        n=len(word1)
        m=len(word2)
        dp=[[0 for i in range(n+1)] for j in range(m+1)]
        
        for i in range(1,n+1):
            dp[0][i]=i
            
        for j in range(1,m+1):
            dp[j][0]=j
            
        for i in range(1,m+1):
            for j in range(1,n+1):
                if(word1[j-1]==word2[i-1]):
                    dp[i][j]=dp[i-1][j-1]
                else:
                    dp[i][j]=1+min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j])
        return dp[m][n]