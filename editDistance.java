/*
Problem: https://leetcode.com/problems/edit-distance/
O(n1 * n2)
*/
class Solution {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        
        if (n1 == 0 && n2 == 0) return 0;
        
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;
        
        int dp[][] = new int[n1 + 1][n2 + 1];
        
        // if word1 == "", moves to convert to word2 is the number of letters in word2
        for (int i = 0; i <= n2; ++i) {
            dp[0][i] = i;
        }
        
        // if word2 == "", moves to convert to word1 to "" is the number of letters in word1
        for (int i = 0; i <= n1; ++i) {
            dp[i][0] = i;
        }
        
        for (int i = 1; i <= n1; ++i) {
            for (int j = 1; j <= n2; ++j) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // dp[i-1][j] -> replace
                    // dp[i-1][j-1] -> delete
                    // dp[i][j-1] -> insert
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                }
            }
        }
        
        return dp[n1][n2];
    }
}