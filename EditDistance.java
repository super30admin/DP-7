public class EditDistance {
    // TC : O(m *n)
    // SC : O(m * n)
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;

        int n = word1.length();
        int m = word2.length();

        if(m > n) return minDistance(word2, word1);

        int[][] dp = new int[m + 1][n + 1];

        // First row
        for(int j=0;j < n + 1; j++){
            dp[0][j] = j;
        }

        // First column
        for(int i=0; i < m + 1;i++) {
            dp[i][0] = i;
        }

        for(int i=1; i< m + 1; i++) {
            for(int j=1; j < n + 1;j++) {
                if(word1.charAt(j - 1) == word2.charAt(i - 1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }
        return dp[m][n];
    }
}
