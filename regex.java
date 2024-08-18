// Time Complexity : 0(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public boolean isMatch(String s, String p) {
        int slength = s.length();
        int plength = p.length();
        boolean[][] dp = new boolean[slength + 1][plength + 1];

        dp[0][0] = true;
        // first row
        for (int i = 1; i < plength + 1; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        for (int i = 1; i < slength + 1; i++) {
            for (int j = 1; j < plength + 1; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || (p.charAt(j - 1) == '.')) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (s.charAt(i - 1) == p.charAt(j - 2) || (p.charAt(j - 2) == '.')) {
                        dp[i][j] = dp[i - 1][j] || dp[i][j];
                    }
                }
            }
        }

        return dp[slength][plength];

    }
}