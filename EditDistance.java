class EditDistance {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) {
            return 0;
        }

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m+1][n+1];

        for(int i=0; i<n+1; i++) {
            dp[0][i] = i;
        }

        for(int i=0; i<m+1; i++) {
            dp[i][0] = i;
        }

        for(int i = 1; i < m+1; i++) {
            for(int j = 1; j < n+1; j++) {
                char ch1 = word1.charAt(i-1);
                char ch2 = word2.charAt(j-1);
                if(ch1 == ch2) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                }
            }
        }

        return dp[m][n];
    }
}
