// Time Complexity : O(m*n)
// Space Complexity : O(m*n) can be optimized to O(min(m,n))
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// dp[i][j] represents minimum ops required to convert word1[0,i-1] to word2[0,j-1]
// Every cell has 4 options - del, ins, replace, no change
// If the characters match , we proceed with no change and check for prev strings only (i-1,j-1)
// If the characters don't match, we have 3 options - del, ins, replace
// del - (i-1,j) up
// ins - (i,j-1)  left
// replace - (i-1,j-1) diagonal up-left
// We take the minimum of these 3 options and add 1 to it

class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++)
            dp[0][i] = i;
        for (int i = 1; i <= n; i++)
            dp[i][0] = i;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
            }
        }

        return dp[n][m];
    }
}