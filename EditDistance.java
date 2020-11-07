// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word2.length() + 1;
        int n = word1.length() + 1;
        int dp[][] = new int[m][n];
        
        for(int col = 1; col < n ; col++){
            dp[0][col] = col;
        }
        
        for(int row = 1; row < m ;row++){
            dp[row][0] = row;
            //System.out.println(dp[row][0]);
        }
        
        for(int row = 1; row < m; row++){
            for(int col = 1; col < n; col++){
                if(word1.charAt(col - 1) == word2.charAt(row - 1)){
                    dp[row][col] = dp[row-1][col-1];
                }else{
                    dp[row][col] = Math.min(dp[row-1][col-1], 
                                Math.min(dp[row-1][col], dp[row][col-1])) + 1;
                }
            }
        }
        return dp[m-1][n-1];
    }
}
