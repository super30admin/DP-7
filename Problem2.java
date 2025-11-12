// Time Complexity : O(mn) where m and n are lengths of word1 and word2
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

/**
 * Using dp matrix to keep track of the pattern matches.
 * Pre-fill the first row since "-" -> "-c" will be false but "-c*" will be true since we can use c 0 times. Not selecting the current char will be 2 steps back.
 * Pre-fill the first column with false since pattern is on x axis. "-" pattern will never be able to make "-a" or "-ab"
 *
 * If the current pattern char is *,
 *  then we can match the previous char of pattern to current char of source via exact match or having the . as previous char
 *  OR we can ignore the previous char of pattern
 *
 * If the current pattern char is not *,
 *  then we can match the previous char of pattern to current char of source via exact match or having the . as previous char
 */
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        // "" -> "" will always match
        dp[0][0] = true;

        // if the pattern char is *, then we can match "" with "c*" since c will be used 0 time
        // looking at 2 steps back for that
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 2];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    // current char matches or the pattern has . which will match any one char
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        // using the current char or not using the current char
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        // if the char doesn't match, then we have an option to use this char 0 times which is 2 steps back
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    // regular character match or .
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        // string prior to current char matches or not
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        // character doesn't match
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[m][n];
    }
}