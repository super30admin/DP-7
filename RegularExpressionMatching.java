// Time Complexity : O(m * n)
// Space Complexity : O(m * n)

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        dp[0][0] = true;
        
        //fill first row 
        for(int j = 1; j < n + 1; j++){
            if(p.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 2];
        }
        
        for(int i = 1; i < m + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                if(p.charAt(j - 1)!= '*') {
                    if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                        dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    //zero case
                    dp[i][j] = dp[i][j - 2];
                    //one case
                    if(s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                }
            }
        }
        
        return dp[m][n];
    }
}