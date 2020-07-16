class EditDistance {

    /**
     * Time complexity: O(NM*N) where M is word1 length and N is word2 length
     * Space complexity: O(NM*N) where M is word1 length and N is word2 length
     * 
     * Approach:
     * 1. Using dynamic programming approach as it has repeatitive sub problems
     * 2. We can use 2D dp array. Since we have to find the minimum distance, in the dp array, at any position, its
     * diagonally up count represents the update count, left count represents the delete count and top count
     * represents the add count. So at any particular position we only have to find minimum of these counts.
     
     */
    
    public int minDistance(String word1, String word2) {
        
        if(word1 == null || word2 == null)
            return 0;
        
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[n+1][m+1];
        
        for(int i=0; i<=n; i++) {
            dp[i][0] = i;
        }
        
        for(int i=0; i<=m; i++){
            dp[0][i] = i;
        }
        
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(word2.charAt(i-1) == word1.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }  
         }

        return dp[n][m];
    }
}