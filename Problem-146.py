'''
Leetcode - 72. Edit Distance - https://leetcode.com/problems/edit-distance/
Time complexity - O(M)(N)
space complexity - O(M)(N)
Approach - DP solution
          1) When character _hor == _ro are equal we replace it with the previous ones
          2) when they are not equal we replace it with minimum of previous direction matrix +1
          
'''
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        n=len(word2)+1
        m=len(word1)+1
        dp =[[0 for _ in range(n)] for _ in range(m)]
        
        #filling the dp array with the index of word1 and word2
        for i in range(m):
            dp[i][0]=i
        
        for j in range(n):
            dp[0][j]=j
        
        for i in range(1, m):
            for j in range(1,n):
                #if chars are equal
                if word1[i-1]==word2[j-1]:
                    dp[i][j]=dp[i-1][j-1]
                else:
                    dp[i][j]=min(dp[i-1][j-1],min(dp[i-1][j],dp[i][j-1]))+1
        return dp[-1][-1]
         
        