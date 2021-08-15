class Solution:
    #Approach 1: DP
    """
    TC: O(mn)
    SC: O(mn)
    
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word2)
        n = len(word1)
        dp = [[0] * (n+1) for _ in range(m+1)]
        
        for j in range(n+1):
            dp[0][j] = j
        
        for i in range(m+1):
            dp[i][0] = i
        for i in range(1, m+1):
            for j in range(1, n+1):
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = min(dp[i][j-1], dp[i-1][j-1], dp[i-1][j]) + 1
        return dp[-1][-1]
    """
    
    #approach 2: DP, space optimized
    """
    TC: O(m*n)
    SC: O(n)
    """
    def minDistance(self, word1: str, word2: str) -> int:
        m = len(word2)
        n = len(word1)
        dp = [ i for i in range(n+1)]
        for i in range(1, m+1):
            prev_left = dp[0]
            dp[0] = i
            for j in range(1, n+1):
                temp = dp[j]
                if word1[j-1] == word2[i-1]:
                    dp[j] = prev_left
                else:
                    dp[j] = min(dp[j], prev_left, dp[j-1]) + 1
                prev_left = temp
        return dp[-1]