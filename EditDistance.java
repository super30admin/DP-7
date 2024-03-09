public class EditDistance {
    public int minDistance(String word1, String word2) {
        return minDistance2D(word1, word2);
    }
    // TC: O(M * N) where M is length of word1 and N is length of word2
    // SC: O(N) where N is length of word2
    private int minDistance1D(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int j = 1; j <= n; j++) {
            dp[j] = 1 + dp[j - 1];
        }
        for (int i = 1; i <= m; i++) {
            int diagUp = dp[0];
            dp[0] = i;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = diagUp;
                } else {
                    dp[j] = 1 + Math.min(dp[j - 1], Math.min(dp[j], diagUp));
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
    // TC: O(M * N) where M is length of word1 and N is length of word2
    // SC: O(M * N) where M is length of word1 and N is length of word2
    private int minDistance2D(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int j = 1; j <= n; j++) {
            dp[0][j] = 1 + dp[0][j - 1];
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = 1 + dp[i - 1][0];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[m][n];
    }
}
