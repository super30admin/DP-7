class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if(s.isEmpty() || p.isEmpty()) {
            return false;
        }
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for(int i=1; i<dp[0].length; i++) {
            if(p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }

        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                if(p.charAt(j-1) != '*') {
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    }
                } else {
                    dp[i][j] = dp[i][j-2];
                    //check if one case is available
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }

        return dp[dp.length-1][dp[0].length-1];
    }
}
