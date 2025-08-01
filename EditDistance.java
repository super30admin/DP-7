// TC:  O(m * n)
// SC: O(m * n)
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word2.charAt(j - 1) != word1.charAt(i - 1)) {
                    int add = dp[i - 1][j];
                    int update = dp[i - 1][j - 1];
                    int delete = dp[i][j - 1];
                    dp[i][j] = 1 + Math.min(add, Math.min(update, delete));
                } else {
                    int noChange = dp[i - 1][j - 1];
                    dp[i][j] = noChange;
                }

            }
        }

        return dp[m][n];
    }
}