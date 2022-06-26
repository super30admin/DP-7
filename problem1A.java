class editDistance {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        
        int m = word1.length();
        int n = word2.length();
        if(m < n) return minDistance(word2, word1);
        int[][] dp = new int[m + 1][n + 1];
        
        for(int i = 0; i <= n; i++){
            dp[0][i] = i;
        }
        
        for(int i = 0; i <= m; i++){
            dp[i][0] = i;
        }
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(word2.charAt(j - 1) == word1.charAt(i - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else{
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        
        return dp[m][n];
    }
}

//time complexity O(m * n) where m and n are the length of given strings
//space complexity O(m * n)