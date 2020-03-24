//TC : O(m*n) m length of s, n length of p
//SC : O(m*n) m length of s, n length of p
class Solution {
    public boolean isMatch(String s, String p) {
       
        if(p == null) return s == null;
       
        
        int sl = s.length(),pl = p.length();
        
        boolean[][] dp = new boolean[sl+1][pl+1];
        dp[0][0] = true;
        
        for(int i=1;i<dp[0].length;i++){
            if(p.charAt(i-1) == '*')
                dp[0][i] = dp[0][i-2];
        }
        
        
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                
                //If No *
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }    
                //If yes *
                else if(p.charAt(j-1) == '*'){
                    //Case 0  - without the last two characters
                    dp[i][j] = dp[i][j-2];
                    
                   
                    //Case 1
                    //Letter before * matches string
                     if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                         if(dp[i][j-2] || dp[i-1][j]){
                              dp[i][j] = true;
                         }
                     }
                }
            }
        }
        
        return dp[sl][pl];
    }
}