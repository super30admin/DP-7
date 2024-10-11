//TC: O(m*n)
//SC: O(m*n)
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        
        // dp[i][j] will be true if s[0..i-1] matches p[0..j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // Base case: both strings are empty
        dp[0][0] = true;
        
        // Fill the base cases for an empty string 's' and non-empty pattern 'p'
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2]; // '*' can only match zero occurrences
            }
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char currentS = s.charAt(i - 1);
                char currentP = p.charAt(j - 1);
                
                if (currentP == currentS || currentP == '.') {
                    // If characters match or current pattern is '.', carry over the result from previous match
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (currentP == '*') {
                    // '*' can match zero or more of the previous character
                    // Case 1: '*' matches zero occurrences
                    dp[i][j] = dp[i][j - 2];
                    
                    // Case 2: '*' matches one or more occurrences, previous character matches or is '.'
                    char precedingChar = p.charAt(j - 2);
                    if (precedingChar == currentS || precedingChar == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
}
