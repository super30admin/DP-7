//Time Complexity : O(m*n)
//Space Complexity : O(m*n)
class RegularExpression {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];

        dp[0][0] = true;

        for(int j = 1; j <= n; j++) {
            char pChar = p.charAt(j-1);
            if(pChar == '*') {
                dp[0][j] = dp[0][j-2];
            }
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                char sChar = s.charAt(i-1);
                char pChar = p.charAt(j-1);
                if(pChar == '*') {
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i][j-2];
                    }
                } else {
                    if(pChar == sChar || pChar == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    }
                } 
            }
        }
        return dp[m][n];
    }
}