// Time Complexity : O(m*n)
// Space Complexity :O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        //Fill the first row where we just have to check for the * case that too zero case
        for(int j=1; j<p.length()+1; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }

        for(int i=1; i<s.length()+1; i++){
            for(int j=1; j<p.length()+1; j++){
                //If incoming characters match, get the daigonal up
                if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                //If we have a *
                if(p.charAt(j-1) == '*'){
                    //If the previous element match, then we have one case and zero case
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i-1][j] || dp[i][j-2] ;
                    }
                    //If previous element do not match, we have only zero case
                    else{
                        dp[i][j] = dp[i][j-2] ;
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }
}