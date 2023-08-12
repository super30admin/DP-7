class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word1), len(word2)
        # The code uses DP with a 1D array to optimize space.
        # Time Complexity: O(m * n), where m is the length of word1 and n is the length of word2.
        # Space Complexity: O(n), for the DP array.

        # Use a 1D DP array to store edit distances between substrings
        dp = [0] * (n + 1)

        # Initialize the DP array for base case (empty string)
        for j in range(n + 1):
            dp[j] = j

        # Temporarily store the value at dp[0] to handle the diagonal element
        prev = dp[0]

        # Populate the DP array using bottom-up approach
        for i in range(1, m + 1):
            dp[0] = i  # Initialize the first element of the current row
            for j in range(1, n + 1):
                temp = dp[j]  # Store the current dp[j] value
                if word1[i - 1] == word2[j - 1]:
                    dp[j] = prev
                else:
                    dp[j] = min(dp[j - 1], dp[j], prev) + 1
                prev = temp  # Update prev for the next iteration

            prev = dp[0]  # Reset prev for the next row

        # Return the minimum edit distance (bottom-right corner of DP array)
        return dp[n]
