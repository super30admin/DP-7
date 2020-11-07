class Solution {
    
    // Approach: Dynamic Prog
    // TC: O(M*N)
    // SC: O(M*N)
    
    public boolean isMatch(String s, String p) {
        if(p == null)
            return s == null;
        
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        
        // First row
        for(int i = 1; i < dp[0].length; i++){
            if(p.charAt(i - 1) == '*')
                dp[0][i] = dp[0][i - 2];
        }
        
        // First column anyhow already false
        
        
        // DP array
        // char matches or . => diagonal
        // char = * ==> 2 steps backwards
        //              .* ==> prev char can . as well ==> dp[i-1][j]
        //              a* ==> dp[i-1][j]
        
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                } else if(p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i][j-2];
                    
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        if(dp[i-1][j] || dp[i][j-2])
                            dp[i][j] = true;
                    }
                    
                }
                
                
            }
        }
        
        return dp[s.length()][p.length()];
    }
}