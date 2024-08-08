// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int minDistance(String word1, String word2) {
        int row = word2.length();
        int col = word1.length();

        int dp[][] = new int [row+1][col+1];

        for(int i = 0; i< row+1; i++){
            dp[i][0] = i;
        }
        for(int i = 0; i< col+1; i++){
            dp[0][i] = i;
        }

        for(int i = 1; i< row+1; i++){
            for(int j = 1; j< col+1; j++){
                if(word2.charAt(i-1) == word1.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1] , Math.min(dp[i][j-1], dp[i-1][j]));
                }
            }
        }
        return dp[row][col];
    }
}