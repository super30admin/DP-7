// Time Complexity : O(n*m)
// Space Complexity : O(n*m)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
// DP
class Solution {
    public int minDistance(String word1, String word2) {
       if (word1 == null || word2 == null) {
            return 0;
        }
        int n = word1.length()+1;
        int m = word2.length()+1;
        
        int[][] dp = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            dp[i][0] = i;
        }
        
        for (int j = 0; j < m; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }
        return dp[n-1][m-1];
    }
}