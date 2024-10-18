time;O(m * n)
space;O(m * n)



class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];

        // Empty pattern matches empty string
        dp[0][0] = true;

        // Deal with patterns like a*, a*b*, a*b*c* matching empty string
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j]; // Match 0 or more preceding elements
                    } else {
                        dp[i][j] = dp[i][j - 2]; // Match 0 of the preceding element
                    }
                } else if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1]; // Match current characters
                }else
                {
                    dp[i][j] = false;
                }
            }
        }

        return dp[m][n];
    }
}