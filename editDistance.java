// Time Complexity : O(N*M) 
// Space Complexity : O(N*M) 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    public int minDistance(String word1, String word2) {
       int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n+1][m+1];

        // Populating first row
        for(int i = 0; i <= m; i++)
            dp[0][i] = i;

        // Populating first column
        for(int i = 1; i <= n; i++)
            dp[i][0] = i;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(word2.charAt(j-1) == word1.charAt(i-1))
                    dp[i][j] = dp[i-1][j-1];
                else{
                    int insert = dp[i-1][j];
                    int delete = dp[i][j-1];
                    int replace = dp[i-1][j-1];

                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        return dp[n][m]; 
    }
}
