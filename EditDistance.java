// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : -


// Your code here along with comments explaining your approach

class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2))
            return 0;
        int m = word1.length();
        int n = word2.length();
        if(m > n){
            return minDistance(word2, word1);
        }
        int[][] dp = new int[m + 1][n + 1];

        //top row
        for(int j = 0; j <= n; j++){
            dp[0][j] = j;
        }

        //first column
        for(int i = 0; i <= m; i++){
            dp[i][0] = i;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[m][n];
    }
}