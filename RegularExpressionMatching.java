// Time Complexity : O(m*n)
// Space Complexity : O(m*n) can optimise to O(min(m,n))
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// dp[i][j] represents to s[0,i-1] and p[0,j-1] are matching
// init dp makes * character values to 2 steps back, (empty string with 0 case)
// If its a character or . we check if the elements before are matching (i-1,j-1)
// If its a * and the character before it matches 
//      we check 0 case - use 0 letters (i,j-2)
//      or 1 case - use 1 letter (i-1,j)
// If character before * doesn't match we check 0 case - use 0 letters (i,j-2) only 

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        for (int i = 1; i <= n; i++)
            if (p.charAt(i - 1) == '*')
                dp[0][i] = dp[0][i - 2];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    // 0 2 steps back // 1 just up
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    else
                        dp[i][j] = dp[i][j - 2];
                } else {
                    if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.')
                        dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }
}