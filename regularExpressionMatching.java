//Time Complexity : O(m*n) m = s length, n = p length
//Space Complexity : O(m*n)
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this : no
class Solution {
    public boolean isMatch(String s, String p) {
        
        int sl = s.length();
        int pl = p.length();
        
        boolean[][] dp = new boolean[sl+1][pl+1];
        
        dp[0][0] = true;
        
        for(int j = 1; j < dp[0].length; j ++){
            
            if(p.charAt(j-1) == '*'){
                
                 dp[0][j] = dp[0][j-2];
            }
        }
        
        for(int i = 1; i < dp.length; i ++){
            
            for(int j = 1; j < dp[0].length; j ++){
               
                if(p.charAt(j - 1) != '*'){ //normal case
                    
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                
                else{
                    
                    //zero case
                    
                    dp[i][j] = dp[i][j-2];
                    
                    //1 case if available
                    
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
                        
                        dp[i][j] = dp[i-1][j] || dp[i][j];
                    }
                       
                    
                    
                }
                
            }
        }
        
        return dp[sl][pl];
    }
}