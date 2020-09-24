//Time Complexity : O(m*n)
//Space Complexity : O(m*n)

class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        
        if(p == null) return s == null;  
        // we are going to follow the dp approach
        boolean[][] dp = new boolean[sl+1][pl+1];
        // our first element in dp array will be true
        // we will not be setting the first column false since boolean is false by default
    
        dp[0][0] = true;
        
        // checking the * in the first row take the boolean value from 2 steps back if there is a *
        
        for(int i = 1; i < dp[0].length; i++)
        {
            if(p.charAt(i - 1) == '*')
            {
                dp[0][i] = dp[0][i-2];
            }
        }
        //filling the dp matrix
        for(int i = 1; i < dp.length; i++)
        {
            for(int j = 1; j < dp[0].length; j++)
            {
                // if the characters are same or there is . then take the diagonal value
             if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
             {
                 dp[i][j] = dp[i-1][j-1];
             }
            else if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i][j-2];                 
                   
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