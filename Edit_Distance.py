"""
// Explain your approach in brief:
// Approach: Use Dynamic Programming to calculate the minimum number of operations needed to convert word1 to word2.
// We define dp[i][j] as the minimum operations required to convert the first i characters of word1 into the first j characters of word2.
// Transitions are based on the three allowed operations: insert, delete, and replace.
"""
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word1), len(word2)  # Get the lengths of the two words
        
        # Initialize the DP table with size (m+1) x (n+1)
        dp = [[0] * (n + 1) for _ in range(m + 1)]

        # Base cases: converting one string to an empty string
        for i in range(m + 1):
            dp[i][0] = i  # Cost of deleting all characters in word1
        for j in range(n + 1):
            dp[0][j] = j  # Cost of inserting all characters in word2

        # Fill the DP table row by row
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if word1[i - 1] == word2[j - 1]:  # Characters match
                    dp[i][j] = dp[i - 1][j - 1]  # No additional cost
                else:
                    # Calculate the cost for insert, delete, and replace operations
                    dp[i][j] = 1 + min(
                        dp[i - 1][j],     # Delete (move up in the table)
                        dp[i][j - 1],     # Insert (move left in the table)
                        dp[i - 1][j - 1]  # Replace (move diagonally)
                    )

        # The answer is in the bottom-right cell of the table
        return dp[m][n]
"""
// Time Complexity: O(m * n), where m and n are the lengths of word1 and word2. We compute each cell of the DP table once.
// Space Complexity: O(m * n), as we use a 2D DP table.
// Did this code successfully run on Leetcode: Yes
// Any problem you faced while coding this: Understanding the DP transitions for edge cases was challenging at first but manageable with practice.
"""