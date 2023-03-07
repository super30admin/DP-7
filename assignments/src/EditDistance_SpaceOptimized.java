// Approach: DP bottom up. Create the table first.
// For case: No change: take the value from cell diagonally up left
// For all other cases: 1 + Min(Update, Add, Delete) values in the previously formed adjacent cells.
// Time: O(m*n)
// Space: O(m) where m < n

class EditDistance_SpaceOptimized {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) return 0;
        int m = word2.length(), n = word1.length();
        if (m > n) return minDistance(word2, word1);    // to always have smaller length row
        int[] dp = new int[m+1];     // 1 extra to store the 0th column value
        // fill the first column of every row
        for (int j = 0; j<=m; j++) {
            dp[j] = j;
        }

        for (int i = 1; i<=n; i++) {
            int diagUp = dp[0];
            dp[0] = i;
            for (int j = 1; j<=m; j++) {
                int tmp = dp[j];
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[j] = diagUp;        // diagonally up left
                }
                else {
                    dp[j] = 1 + Math.min(diagUp, Math.min(dp[j], dp[j-1]));
                }
                diagUp = tmp;
            }
        }
        return dp[m];
    }
}