class RegularExpressionMatching {
    
    // Time Complexity: O(n*m)  (where n -> len(s) and m -> len(p))
    // Space Complexity: O(n*m)
    
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        
        for(int i = 1; i <= m; i++){
            if(p.charAt(i-1) == '*')
                dp[0][i] = dp[0][i-2];
        }
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                char schar = s.charAt(i-1);
                char pchar = p.charAt(j-1);
                
                // get the diagonal value
                if(schar == pchar || pchar == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }else if(schar != pchar && pchar != '*'){
                    dp[i][j] = false;
                }
                // case when pchar = '*'
                else{
                    boolean dont_choose = dp[i][j-2];
                    boolean choose = false;
                    if(schar == p.charAt(j-2) || p.charAt(j-2) == '.')
                        choose = dp[i-1][j];
                    
                    dp[i][j] = dont_choose || choose;
                }
            }
        }
        return dp[n][m];
    }
}