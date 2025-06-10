/*
 * Time Complexity : O(m*n)
 * Space Complexity : O(m*n)
 * Did this code successfully run on Leetcode : yes
 * Any problem you faced while coding this : no
 */ 

 class Solution {
    public boolean isMatch(String s, String p) {

        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1]; //adding dummy row+col for calculation

        dp[0][0] = true;
        //1st column is all false as any string cant catch with dummy '-' 
        //top row needs to be calc as we dont have diagonal up 
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2]; //Only 0 case available
            }

        }

        for (int i = 1; i < m + 1; i++) { //s
            for (int j = 1; j < n + 1; j++) { //p
                if (p.charAt(j - 1) == '*') {
                    //if 1 case is available i.e preceding p char is same as s
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }

                } else {
                   
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1]; //diag up left
                    }

                }
            }
        }

        return dp[m][n];

    }
}
