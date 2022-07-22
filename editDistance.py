'''
time complexity: O(mn)
m and n are len of word1 and 2
space complexity: O(mn)
'''
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        dp = [[0 for _ in range(len(word1)+1)] for i in range(len(word2)+1)]
        
        
        for i in range(len(dp)):
            dp[i][0] = i
        
        for j in range(len(dp[0])):
            dp[0][j] = j
            
        
        for i in range(1,len(dp)):
            for j in range(1,len(dp[0])):
                if(word1[j-1]==word2[i-1]):
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = 1 + min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])
        return dp[-1][-1]