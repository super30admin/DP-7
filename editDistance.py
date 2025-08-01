"""
Use a 2D DP table where dp[i][j] = min edits to convert word1[0:i] to word2[0:j].
If characters match, dp[i][j] = dp[i-1][j-1]; else take min of insert, delete, or replace.
Fill table bottom-up; result is dp[m][n].
"""
"""
Time Complexity: O(m × n)
Space Complexity: O(m × n)
"""


class editDistance:
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word1), len(word2)
        dp = [[0] * (n + 1) for _ in range(m + 1)]

        # Initialize base cases
        for i in range(m + 1):
            dp[i][0] = i
        for j in range(n + 1):
            dp[0][j] = j

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if word1[i - 1] == word2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1]
                else:
                    dp[i][j] = 1 + min(
                        dp[i - 1][j],  
                        dp[i][j - 1],    
                        dp[i - 1][j - 1]
                    )

        return dp[m][n]

if __name__ == "__main__":
    obj = editDistance()
    print(obj.minDistance("horse", "ros"))  
    print(obj.minDistance("intention", "execution")) 
