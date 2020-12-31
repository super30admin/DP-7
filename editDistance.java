// Time Complexity : o(mn)
// Space Complexity : 0(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Leetcode : 72

class Solution {
    public int minDistance(String word1, String word2) {
        
        //Original word
        int n = word1.length();
        //Goal
        int m = word2.length();
        
        //row+1 && col+1
        int[][] dp = new int[m+1][n+1];
       
        //Fill the rows
        for(int i = 1 ; i <= m ; i++){
            dp[i][0] = i;
        }
        
        //Fill the columns
        for(int i = 1 ; i <= n ; i++){
            dp[0][i] = i;
        }
        
        
        for(int i = 1 ; i <= m; i++){
            for(int j = 1 ; j <= n ; j++){
                //If the last name matches, simple copy the diagonal value
                if(word1.charAt(j-1)==word2.charAt(i-1)){
                    dp[i][j]  = dp[i-1][j-1];
                }
                //Otherwise fill the minimum from the (adjacent up, left and diag) + 1 to curr
                else{
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        
        return dp[m][n];
    }
}

