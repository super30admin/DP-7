"""
Problem : 1

Time Complexity : O(m*n)
Space Complexity : 
Approach 1 - O(m*n)
Approach 2 - O(n)

Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No


"""

# Edit Distance


# Approach - 1
# DP Matrix

class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        m=len(word1)
        n=len(word2)
        dp=[[0 for _ in range(n+1)] for _ in range(m+1)]
        for i in range(1,n+1):
            dp[0][i]=i
        for i in range(1,m+1):
            dp[i][0]=i
        
        for i in range(1,m+1):
            for j in range(1,n+1):
                if word1[i-1]==word2[j-1]:
                    dp[i][j]=dp[i-1][j-1]
                else:
                    dp[i][j]=1+min(dp[i-1][j],min(dp[i-1][j-1],dp[i][j-1]))

        return dp[m][n]

        
# Approach - 2
# DP array
    
class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        m=len(word1)
        n=len(word2)
        dp=[0 for _ in range(n+1)]
        for i in range(1,n+1):
            dp[i]=i
        
        for i in range(1,m+1):
            diagUp=dp[0]
            for j in range(0,n+1):
                temp=dp[j]
                if j==0:
                    dp[j]=i
                else:
                    if word1[i-1]==word2[j-1]:
                        dp[j]=diagUp
                    else:
                        dp[j]=1+min(dp[j],min(diagUp,dp[j-1]))
                diagUp=temp

        return dp[n]