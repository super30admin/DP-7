//time o(mn)
//space o(mn)
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        //empty string compared with empty string
        dp[0][0] = true;
        
        //first row
        for(int i=1; i<dp[0].length; i++) {
            //consider zero case for "*"
            if(i>1 && p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }
        
        
        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(j>1 && p.charAt(j-1) == '*') {
                    //zero case
                    dp[i][j] = dp[i][j-2];
                    //one case if elgible
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2)== '.') {
                        if(dp[i][j] || dp[i-1][j]) {
                            dp[i][j] = true;
                        }
                    }
                }
            }
        }
        return dp[m][n];
    }
}