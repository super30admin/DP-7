// TC : O(mn)
// SC : O(mn)
class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean [][] dp = new boolean [m+1][n+1];
        dp[0][0] = true;
        for(int i=1;i<=n;i++) {
            if(p.charAt(i-1) == '*')
                dp[0][i] = dp[0][i-2];
        }

        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {

                if(p.charAt(j-1) == '*') {
                    //matching case
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
                        dp[i][j] = dp[i][j-2] || dp[i-1][j]; 
                    } else {
                        dp[i][j] = dp[i][j-2];
                    }

                } else {
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = false;
                    }

                }
            }
        }
        return dp[m][n];
    }
}