


class Solution {
    public boolean isMatch(String s, String p) {
     
        int n = s.length()+1;
        int m = p.length()+1;
        
        boolean[][] dp = new boolean [n][m];
        
        //fill the initial column
        dp[0][0] = true;
        
        //fill the initial row
        for(int j=1; j<m ;j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        
        for(int i=1; i<n; i++){
            for(int j=1 ; j<m ; j++){
                //case 1 & 2
                if(p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                // case 3;
                
                if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i][j-2];
                    if(p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1)){
                        dp[i][j] =  dp[i][j-2] || dp[i-1][j];
                    }
                }
                
                
            }
        }
        return dp[n-1][m-1];
    }
}
