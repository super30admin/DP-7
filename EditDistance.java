//The approach here is to use DP to store the previously solved sub-problems
//Here, we implemented bottom-up DP using tabulation
//At each index, we check if the character is same for word1 and word2. If they are same, we take the top-left from table
//If they are not same, then we take the minimum of update, delete and insert operations from table, add one to it and add it tto the table
//In the end, we return the last value in table
//Time complexity: O(m*n) where m and n are lengths of word1 and word2
//Space Complexity: O(m*n) for dp matrix
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int j = 0; j <= n; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i<=m; i++){
            dp[i][0] = i;
            for(int j = 1; j <= n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                }
            }
        }
        return dp[m][n];
    }
}