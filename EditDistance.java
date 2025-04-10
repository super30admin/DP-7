/*
 * TC: O(m*n)
 * SC: O(m*n)
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                // when word1 is empty edit distance = length of word2
                if(i == 0) {
                    dp[i][j] = j;
                    continue;
                }
                // ... and vice versa
                if(j == 0) {
                    dp[i][j] = i;
                    continue;
                }
                // for matching chars, no edit is required, just take the prev diag value
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                // otherwise take the min of the 3 prev neighbours and add 1
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
            }
        }
        return dp[m][n];
    }
}
