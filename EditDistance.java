// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Approach : This problem has repeated sub problems, so we need to implement DP. I have done it using a dp matrix with tabulation technique. We found a pattern
// that while parsing word1 if the char is equal to the char at word2 at same position then we consider the top left diagonal of the dp matrix current position,
// else it's minimum of all three insert, delete, update operations + 1 for the current operation. Finally the [n][m] value of the dp matrix gives the minimum distance.

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if(n > m){
            return minDistance(word2, word1);
        }
        int[][] dp = new int[n+1][m+1];
        for(int i = 0; i <= m;i++){ //filling the first row
            dp[0][i] = i;
        }
        for(int i = 0; i <= n;i++){
            dp[i][0] = i; //filling the first column
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                char ch = word1.charAt(j-1); //char on the column
                if(ch == word2.charAt(i-1)){ //checking equals with char on the row
                    dp[i][j] = dp[i-1][j-1]; //top left diagonal
                }else{
                    dp[i][j] = 1+  Math.min(dp[i][j-1], Math.min(dp[i-1][j-1], dp[i-1][j])); //minimum of insert, delete, update operations.
                }
            }
        }
        return dp[n][m];
    }
}