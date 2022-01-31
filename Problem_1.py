class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if word1 == word2:
            return 0
        n, m = len(word1), len(word2)
        dp = [[0 for j in range(n+1)] for i in range(m+1)]
        
        for j in range(len(dp[0])):
            dp[0][j] = j

        for i in range(len(dp)):
            dp[i][0] = i
            
        for i in range(1, len(dp)):
            for j in range(1, len(dp[0])):
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
                    
        return dp[m][n]

# Time Complexity: O(m * n)
# Space Complexity: O(m * n)


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if word1 == word2:
            return 0
        n, m = len(word1), len(word2)
        if m > n:
            return self.minDistance(word2, word1)
        dp = [0] * (n + 1)
        
        for j in range(len(dp)):
            dp[j] = j
        
        prev = 0
        for i in range(1, m+1):
            temp = dp[0]
            dp[0] = i
            for j in range(1, n+1):
                prev = dp[j]
                if word1[j-1] == word2[i-1]:
                    dp[j] = temp
                else:
                    dp[j] = min(dp[j], dp[j-1], temp) + 1
                temp = prev
                    
        return dp[n]

# Time Complexity: O(m * n)
# Space Complexity: O(n)