// In this problem, using a dp boolean matrix to store true if the subproblem string can be formed using subproblem pattern else 
// false. Whenever the chars are equal, our ans lies in diagonal place. Whenever there is a '*' operator, and if the char preceding
// to the * is same as the char in string to form, then ans lies in 1-case which is right above or 0-case which is 2 cols behind. 
// And if not equal then 0-case. Final ans is in last cell.

// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public boolean isMatch(String s, String p) {
        // Base case
        if (s.equals(p)) {
            return true;
        }
        // Row having the string to form
        int m = s.length();
        // Column having the pattern
        int n = p.length();
        // plus size dp array bcoz of empty string
        boolean[][] dp = new boolean[m + 1][n + 1];
        // First we can make empty string from empty string so true
        dp[0][0] = true;
        // For first row, since are trying to form the "", no matter whatever char, we
        // cannot match any char to empty string. Only for '*'' we can choose to select
        // 0 char, and that's why only 0-case applicable for first row
        for (int j = 1; j < n + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                // So update first row if the pattern char is '*', to the 0-case which lies two
                // columns behind
                dp[0][j] = dp[0][j - 2];
            }
        }
        // For remaining cells, compute the ans
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // Check if the chars are equal, ans lies in the diagonal cell, because example
                // ab to match with b, so b and b will cancel out each other and a to match with
                // '', so this subproblem we might have already solved earlier in diagonal cell
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // If it is *, then change the dp ans to 0-case ans, 2 cols behind
                else if (p.charAt(j - 1) == '*') {
                    // 0-case
                    dp[i][j] = dp[i][j - 2];
                    // If it is *, and the preciding char to * is matching to current string char,
                    // then we also have 1-case to consider, so whichever gives true consider that
                    // else false
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        // Final ans in last cell
        return dp[m][n];

    }
}