// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
/*
 * Take the minmum of insert, delete and replace option and add one to current cell if chars dont macth.
 * If chars match, take the diagonal up value.
 */

class Solution {
    public int minDistance(String source, String target) {
        int m = target.length(), n = source.length();
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 0;
        for(int i=1; i<=m; i++){
            dp[i][0] = i;
        }

        for(int i=1; i<=n; i++){
            dp[0][i] = i;
        }

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(source.charAt(j-1) == target.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    int insert = dp[i-1][j];
                    int delete = dp[i][j-1];
                    int replace = dp[i-1][j-1];
                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        return dp[m][n];
    }
}