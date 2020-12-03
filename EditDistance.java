// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
// construct a dp[m+1][n+1] array dp[0][:] indicates cost to convert ''  to word2, insertion, dp[:][0] indicates cost to convert word2 to '', deletion
// constuct dp array as cost to convert substring in word1 to substring in word2, min of operations of deletion, insertion and replacement   
// if chars match dp[i][j] = dp[i-1][j-1], else dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m+1][n+1];
        
        for(int i=0; i<m+1; i++){
            dp[i][0] = i;
        }
        
        for(int j=0; j<n+1; j++){
            dp[0][j] = j;
        }
        
        for(int i=1; i<m+1; i++){
            for(int j=1; j<n+1; j++){
                if(word1.charAt(i-1)!=word2.charAt(j-1)){
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]))+1;
                }
                else{
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        
        return dp[m][n];
    }
}