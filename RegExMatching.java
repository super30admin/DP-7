//TC : O(M*N)
//SC : O(M*N)

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        
        boolean [][] dp = new boolean[m+1][n+1];
        
        
        dp[0][0] = true; // Empty string == Empty String
       
        for(int j = 1; j<= n; j++){
            // First row  -- Match with Empty string
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2]; //2 step back
            }
        }
        
        
        for(int i = 1; i<= m; i++){
            for(int j = 1; j<= n; j++){
                if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i][j-2] ;// 2 steps back if char is *
                        
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){ // checking character before * , IF IT is same or '.'
                        dp[i][j] = dp[i][j] || dp[i-1][j]; //(Doing XOR)
                    }
                } else{
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                        dp[i][j] = dp[i-1][j-1]; // Diagonal-Up If char matches 
                    }
                }
                
            }
        }
        
        return dp[m][n];
    }
}