// Time complexity: O(m*n)
// Space complexity: O(m*n)
class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;

        int m = word1.length();
        int n = word2.length();
        if (n > m) {
            minDistance(word2, word1);
        }

        int[][] dp = new int[m+1][n+1];

        // Fill frist row
        for (int j=0; j < n +1; j++) {
            dp[0][j] = j;
        }
        // fill first col
        for (int i=0; i < m+1; i++) {
            dp[i][0] = i;
        }

        // fill dp array
        for (int i=1; i < m+ 1; i++) {
            for (int j=1; j< n+1; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }


        return dp[m][n];

    }
}