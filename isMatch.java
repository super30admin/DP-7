class Solution {
    public boolean isMatch(String s, String p) {
        //if(s == null || s.length() == 0) return false;
        //if(p == null || p.length() == 0) return false;
        if(s.length() == 0 && p.length() == 0) return true;
        int m = s.length();
        int n = p.length();
        boolean [][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        
        //first col
        for(int i = 1; i < dp.length; i++){
            dp[i][0] = false;
        }
        
        //first row 
        for(int j = 1; j < dp[0].length; j++){
            if(p.charAt(j - 1) == '*' ){
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(p.charAt(j - 1) != '*'){
                    if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'){
                        dp[i][j] = dp[i - 1][j - 1];
                    }else{
                        dp[i][j] = false;
                    } 
                }
                else{
                    dp[i][j] = dp[i][j - 2];
                    
                    if(s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'){                                   dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }   
            
                }
             }
        }
        return dp[m][n];
    }
}
