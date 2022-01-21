# Time: O(m*n)
# Space: O(m*n)
class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        dp = [[None for i in range(len(word1)+1)] for j in range(len(word2)+1)]
        dp[0][0] = 0
        for i in range(1, len(word1)+1):
            dp[0][i] = 1 + dp[0][i-1]
        for i in range(1, len(word2)+1):
            dp[i][0] = 1 + dp[i-1][0]
        #print(dp)
        for i in range(1, len(word2)+1):
            for j in range(1, len(word1)+1):
                if word2[i-1] == word1[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = min(1+dp[i-1][j], min(1+dp[i][j-1], 1+dp[i-1][j-1]))
        return dp[len(word2)][len(word1)]
            
        
