// Time Complexity: O(m * n) — fill each DP state once using a rolling 1D array.
// Space Complexity: O(n) — one array of length n+1 for the current row (plus O(1) temps).

// Initialize dp[j] = j for converting "" → word2[0..j]; iterate i = 1..m, set dp[0] = i each row.
// Sweep j = 1..n keeping diagUp = previous row’s dp[j-1]; if chars match, dp[j] = diagUp.
// Else dp[j] = 1 + min( dp[j] (delete), dp[j-1] (insert), diagUp (replace) ); answer is dp[n].

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[] dp = new int[n + 1];

        for (int j = 1; j <= n; j++) {
            dp[j] = j;
        }

        for (int i = 1; i <= m; i++) {
            int diagUp = dp[0];
            for (int j = 0; j <= n; j++) {
                int temp = dp[j];
                if (j == 0) {
                    dp[j] = i;
                } else {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[j] = diagUp;
                    } else {
                        dp[j] = 1 + Math.min(dp[j], Math.min(diagUp, dp[j - 1]));
                    }
                }
                diagUp = temp;
            }
        }

        return dp[n];
    }
}
