/*
Problem: https://leetcode.com/problems/regular-expression-matching/
TC: O (n1 * n2)
*/

class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) return true;
        
        int m = s.length();
        int n = p.length();
        boolean dp[][] = new boolean[m + 1][n + 1];
        
        // 1. Empty string matches empty pattern
        dp[0][0] = true;
        
        // 2. Set first column as false => Non-empty string will never match empty pattern
        for (int i = 1; i <= m; ++i)
            dp[i][0] = false;
        
        // 3. Set first row.
        // First row => empty string and non-empty pattern.
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                // Check if empty string can be formed by pattern.
                // This is found by considering the character before * 0 times.
                // This value is found two columns behind in the same row.
                dp[0][j] = dp[0][j-2];
            } else {
                // character in p is a lower case letter. This will never match an empty pattern
                dp[0][j] = false;
            }
        }
        
        // 4.Fill dp matrix
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j-1) != '*') {
                    // chars at s and p match, or char at is . (which matches with everything),
                    // get diagonal left value to see if a match was found previously.
                    if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    } // Else, set to false. False by default in Java
                } else {
                    // If char before * in p matches char at s, we should check if:
                    // 1. A match can be found by considering at least one occurrence of the character in before * in p
                    // 2. A match is possible by considering 0 occurrences of the char before * in p
                    if (p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    } else {
                        // Since the characters don't match, check if possible to find a match by considering 0 occurrences of char before * in p
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
}