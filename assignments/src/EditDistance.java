// Approach: DP bottom up. Create the table first.
// For case: No change: take the value from cell diagonally up left
// For all other cases: 1 + Min(Update, Add, Delete) values in the previously formed adjacent cells.
// Time: O(m*n)
// Space: O(m*n)

class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) return 0;
        int m = word1.length(), n = word2.length();
        if (m > n) return minDistance(word2, word1);
        int[][] dp = new int[m+1][n+1];     // 1 extra to store the 0th row and column values
        // fill the top row
        for (int j = 0; j<=n; j++) {
            dp[0][j] = j;
        }
        // fill the first column
        for (int i = 0; i<=m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i<=m; i++) {
            for (int j = 1; j<=n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];        // diagonally up left
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        return dp[m][n];
    }
}