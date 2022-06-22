#Edit Distance
# // Time Complexity : O(N*M)
# // Space Complexity :O(N)
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:

        dp=[i for i in range(len(word2)+1)] 
        
        for i in range(1, len(word1)+1):
            prev=dp[0]
            dp[0]=i
            
            for j in range(1, len(dp)):
                temp = dp[j]
                if word2[j-1]==word1[i-1]:
                    dp[j] = prev
                else:
                    dp[j]= 1+min(prev,dp[j-1],dp[j])
                prev = temp
        return dp[-1]
                    
                    
                    