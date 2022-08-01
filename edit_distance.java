// Time Complexity :O(mn)
// Space Complexity :O(mn)
// Did this code successfully run on Leetcode :yes

class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len2 + 1][len1 + 1];
        // filling first column
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        // filling first row
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        // all other
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // if characters are same
                if (word2.charAt(i - 1) == word1.charAt(j - 1)) {
                    // no need to do any action, just take diagonal up
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // take min of update delete and add and add 1 to it
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
                }
            }
        }
        return dp[len2][len1];
    }
}