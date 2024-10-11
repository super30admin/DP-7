//TC: O(m*n)
//SC: O(m*n)
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // dp[i][j] will store the minimum edit distance between word1[0..i-1] and word2[0..j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Deleting all characters from word1 to match an empty word2
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Inserting all characters from word2 to match an empty word1
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],   // Delete
                                            Math.min(dp[i][j - 1],   // Insert
                                                     dp[i - 1][j - 1])); // Replace
                }
            }
        }
        
        return dp[m][n];
    }
}
