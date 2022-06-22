public class Problem1 {

    // TC : O(m*n)
    // SC : O(m*n)
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) return 0;

        if (word1.length() < word2.length()) return minDistance(word2, word1);

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // first row
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        // first column
        for (int j = 0; j <= m; j++) {
            dp[j][0] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }

            }
        }
        return dp[m][n];
    }

    // 1D DP Solution
    // TC : O(m*n)
    // SC : O(n)
    public int minDistance1(String word1, String word2) {
        if (word1.equals(word2)) return 0;

        if (word1.length() < word2.length()) return minDistance1(word2, word1);

        int m = word1.length();
        int n = word2.length();

        int[] dp = new int[m + 1];

        //first row
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            int temp = dp[0];
            dp[0] = i;

            for (int j = 1; j <= n; j++) {
                int prev = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = temp;
                } else {
                    dp[j] = Math.min(dp[j - 1], Math.min(dp[j], temp)) + 1;
                }
                temp = prev;
            }
        }
        return dp[n];
    }
}
