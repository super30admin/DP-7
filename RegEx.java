class RegEx {

    /**
     * Time complexity: O(N*M) where M is s length and N is p length
     * Space complexity: O(N*M) where M is s length and N is p length
     * 
     */

    public boolean isMatch(String s, String p) {
        
        boolean dp[][] = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        
        for(int i=1; i< dp[0].length; i++) {
            if(i > 1 && p.charAt(i-1) == '*'){
                dp[0][i] = dp[0][i-2];
            }
        }
        
        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(j > 1 && p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i][j-2];
                    
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
                
            }
        }
        
        return dp[s.length()][p.length()];
    }
}