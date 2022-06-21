// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    public int minDistance(String word1, String word2) {
        
        if(word1.length() == 0 && word2.length() == 0) {
            return 0;
        }

        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m+1][n+1];
        
        for(int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        
        for(int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(word1.charAt(i-1) != word2.charAt(j-1)) {
                    // System.out.println(i + ", " + j + " = " +  Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1);
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                } else {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        
        return dp[m][n];
    }
}