// Time Complexity: O(m*n)
// Space Complexity: O(m*n) DP array

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m+1][n+1];

        // row
        for(int i = 0 ; i < dp[0].length ; i++){
            dp[0][i] = i;
        }

        // column
        for(int i = 0 ; i < dp.length ; i++){
            dp[i][0] = i;
        }

        for(int i = 1 ; i < dp.length ; i++){
            for(int j = 1; j < dp[0].length ; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){  // dp matrix has extra row and col
                    dp[i][j] = dp[i-1][j-1];     // taking value from diagonal up
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])); // 1 step plus minimum of addition,deletion and update
                }
            }
        }
        return dp[m][n];
    }
}
