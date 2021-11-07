// Time Complexity : O(sl x pl)
// Space Complexity : O(sl x pl)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length(); int pl = p.length();
        if(s.equals(p)) return true;
        boolean [][] dp = new boolean[sl + 1][pl + 1];
        dp[0][0] = true;
        
        // top row
        for(int j=1; j<dp[0].length; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        dp[i][j] = dp[i-1][j-1]; // diag up
                    }
                } else{ // *
                    // zero case
                    dp[i][j] = dp[i][j-2];
                    // one case, if available
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[sl][pl];
    }
}