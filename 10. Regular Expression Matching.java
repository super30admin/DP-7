class Solution { //Time and space of O(SP)
    public boolean isMatch(String s, String p) {
        
        if(p == null ) return s==null;
        
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        
        //empty string check
        dp[0][0] = true;
        
        // row:string and col: pattern a*
        
        for(int i= 1 ; i< dp[0].length ;i++){
            if(p.charAt(i-1) == '*'){
                dp[0][i] = dp[0][i-2];
            }
        }
        
        for(int i = 1 ; i< dp.length ; i++){
            for(int j = 1 ; j<dp[0].length ; j++){
                //Case 1: i-1 and j-1 are equal or j-1 = '.'
                if(p.charAt(j-1) == s.charAt(i-1)|| p.charAt(j-1)=='.'){
                    dp[i][j] = dp[i-1][j-1];
                }else if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i][j-2]; // aba* -> ab
                    
                    if(p.charAt(j-2) == '.'|| p.charAt(j-2) == s.charAt(i-1)){
                        if(dp[i][j-2]||dp[i-1][j]){ // a* -> Skip a* a (a*)
                            dp[i][j] = true;
                        }
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}