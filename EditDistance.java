// Time Complexity : O(MN)
// Space Complexity : O(MN)
// Did this code successfully run on Leetcode :YES
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    public int minDistance(String word1, String word2) {

        if (word1.equals(word2)) return 0;

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // initialize
        // word1 :: m and word2 :: n
        for (int i = 1; i <= m ; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n ; j++) {
            dp[0][j] = j;
        }

        // Start to compare word1 and word2
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // first take min of all the ops -> Insert, update, delete
                int min = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                // If chars match
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // skip the op // carry prev value
                } else {
                    dp[i][j] = min + 1;
                }
            }
        }
        return dp[m][n];
    }
}