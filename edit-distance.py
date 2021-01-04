# Time Complexity: O(m*n)
# Space Complexity: O(m*n)
class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        dp = [[0 for _ in range(1+len(word2))] for _ in range(1+len(word1))]
        
        # Base Cases
        for col in range(1 + len(word2)):
            dp[0][col] = col
            
        for row in range(1 + len(word1)):
            dp[row][0] = row
            
        # Tabulate
        for row in range(1, 1 + len(word1)):
            for col in range(1, 1 + len(word2)):
                if word1[row - 1] == word2[col - 1]:
                    s = 0
                else:
                    s = 1
                dp[row][col] = min(dp[row-1][col] + 1, dp[row][col-1] + 1, dp[row-1][col-1] + s)
        return dp[-1][-1]