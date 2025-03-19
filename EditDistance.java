// In this problem, using dp, if the current character of pattern matches the current character of string then answer lies diagonally
// above, and if it doesnt match, ans is 1 + min(left, up, diagonally above).

// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public int minDistance(String word1, String word2) {
        // Base Case
        if (word1.equals(word2)) {
            return 0;
        }
        int m = word1.length();
        int n = word2.length();
        // Dp matrix of length + 1 bcoz we have empty strings at start
        int[][] dp = new int[m + 1][n + 1];
        // Fill first column with 0,1,2,3,..,m
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        // Fill first row with 0,1,2,3,..,n
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = j;
        }
        // Then for each cell
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // If the char matches, ans lies upper left - diagonal
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Else it is min of L,U,UL + 1
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        // Our ans lies in the last cell
        return dp[m][n];
    }
}