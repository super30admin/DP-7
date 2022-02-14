// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// we use dp to solve this
// We check if the character in the pattern is same as the string or is . then we take the diagonal
// we encounter the * we take the value two steps to  the left
// we check the previous char if it matches the we or the value and the above value

class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;
        if (s.equals(p))
            return true;
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j < dp[0].length; j++) {
            if (p.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 2];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (p.charAt(j - 1) != '*') {
                    if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }

                } else {
                    dp[i][j] = dp[i][j - 2];
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}