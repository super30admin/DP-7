//TimeComplexity:O(m*n)

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean [][]dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int j = 1; j<= p.length();j++) {
            if(p.charAt(j-1) == '*') {
                dp[0][j] = dp[0][j-2];
            }
        }
        for(int i = 1; i<= s.length();i++) {
            for(int j = 1; j<= p.length();j++) {
                if(p.charAt(j-1) != '*') {
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                }
            } else {
                if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-2];
                } else {
                    dp[i][j] = dp[i][j-2];
                }
            }
        }
        
    }
    return dp[s.length()][p.length()];
}
}