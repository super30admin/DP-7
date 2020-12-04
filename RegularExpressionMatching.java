// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
// we try to match char by char on both strings
// if we encounter . it is allowed, for the case of * at pos i check i-2 if (i-1) char can be skipped or if char at i-1 matches
// a bit unclear about this case 
 

class Solution {
    public boolean isMatch(String s, String p) {
        if(p==null) return s==null;
        
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        
        for(int i=1; i<dp[0].length; i++){
            if(p.charAt(i-1)=='*'){
                dp[0][i] = dp[0][i-2];
            }
        }
        
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                
                if(p.charAt(j-1)==s.charAt(i-1) || p.charAt(j-1)=='.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(p.charAt(j-1)=='*'){
                    dp[i][j] = dp[i][j-2];
                    
                    if(p.charAt(j-2)=='.' || p.charAt(j-2) == s.charAt(i-1)){
                        if(dp[i][j-2] || dp[i-1][j])
                            dp[i][j] = true;
                    }
                }
            }
        }
        
        return dp[s.length()][p.length()];
    }
}