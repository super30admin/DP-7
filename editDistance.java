// Problem1 Edit Distance (https://leetcode.com/problems/edit-distance/)

// Time Complexity : O(m*n) 
// Space Complexity : O(m*n), optimized o(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach in three sentences only
/*
 * Here, first fill all with ith value and then if word1.charAt(i-1) == word2.charAt(j-1) then fill that block with diagonal up element else
 * take 1+(min(left, diagonal up left, up)) elements. Finally return last element.
 */
// 1
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int [][]dp = new int[m+1][n+1];
        for(int i = 1; i <= n ; i++){
            dp[0][i] = i;
        } 
        for(int i = 1; i <= m; i++){
            dp[i][0] = i;
            for(int j = 1; j <=n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = 1+ Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                }
            }
        }
        return dp[m][n];
    }
}
// 2
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int []dp = new int[n+1];
        for(int i = 1; i <= n ; i++){
            dp[i] = i;
        } 
        for(int i = 1; i <= m; i++){
            int diagUp = dp[0];
            dp[0] = i;
            for(int j = 1; j <=n; j++){
                int temp = dp[j];
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[j] = diagUp;
                }
                else{
                    dp[j] = 1+ Math.min(dp[j], Math.min(diagUp, dp[j-1]));
                }
                diagUp = temp; 
            }
        }
        return dp[n];
    }
}

