// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : -


// Your code here along with comments explaining your approach

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p))
            return true;
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true; // Empty string matches empty string

        // First row
        for(int j = 1; j <= n; j++){
            // 0 case when the character is *, there is no 1 case for the first row
            if(p.charAt(j - 1) == '*'){
                dp[0][j] = dp[0][j - 2];
            }
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(p.charAt(j - 1) != '*'){
                    if(p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.'){
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    // 0 case for *
                    dp[i][j] = dp[i][j - 2];
                    // 1 case only if the preceding character in the p string and current character of the s string match
                    if(p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}