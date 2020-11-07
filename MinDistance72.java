class Solution {
    // Approach: Dynamic Prog
    // TC: O(M*N)
    // SC: O(M*N)
    public int minDistance(String word1, String word2) {
        int m = word1.length() + 1;
        int n = word2.length() + 1;
        
        int[][] dp = new int[m][n];
        
        for(int i = 0; i < n; i++){
            dp[0][i] = i;
        }
        
        for(int i = 0; i < m; i++){
            dp[i][0] = i;
        }
        
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1))      // char match
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;            // Min among three operations
            }
        }
        return dp[m-1][n-1];    
    }
}