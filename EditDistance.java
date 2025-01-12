// Approach:
// Time : O(m*n)
// Space : O(m*n)
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m+1][n+1]; // extra row and extra column for "_"
        // fill the top row - 1 to n (columns)
        // go through all dp columns
        for(int i=1;i<dp[0].length;i++){
            dp[0][i]= i;
        }
        
        // fill the first column - O to m (rows)
        // go through all dp rows
        for(int i=0;i<dp.length;i++){
            dp[i][0]= i;
        }

        // iterate through the dp - rows and columns
        for(int i=1;i<dp.length;i++){ // word1
            for(int j=1;j<dp[0].length;j++){ // word2
                if(word1.charAt(i-1) == word2.charAt(j-1)){// characters in word starts from i-1 & j-1
                    dp[i][j] = dp[i-1][j-1];
                } else{
                    // min of update , delete and add case
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
                }
            }
        }
        return dp[m][n];
    }
}