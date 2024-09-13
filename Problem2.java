//Time = O(mn), lengths of strings
//Space = O(mn)

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        
        // base case: an empty pattern matches an empty string
        dp[0][0] = true;
        
        // fill in the first row of the table
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j-1) == '*') {
                // '*' can match 0 occurrences of the previous character,
                // so we need to skip over the previous 2 characters in the pattern
                dp[0][j] = dp[0][j-2];
            }
        }
        
        // fill in the rest of the table using dynamic programming
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sChar = s.charAt(i-1);
                char pChar = p.charAt(j-1);
                if (sChar == pChar || pChar == '.') {
                    // the current characters match, so we can use the previous match
                    dp[i][j] = dp[i-1][j-1];
                } else if (pChar == '*') {
                    // zero or more occurrences of the previous character
                    if (dp[i][j-2]) {
                        // zero occurrences, so we skip over the previous 2 characters in the pattern
                        dp[i][j] = true;
                    } else if (sChar == p.charAt(j-2) || p.charAt(j-2) == '.') {
                        // one or more occurrences, so we use the previous match in the string
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
        }
        
        // the answer is stored in the bottom-right corner of the table
        return dp[m][n];
    }
}
