// Time Complexity : O(m * n)
// Space Complexity : O(m * n)

class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        if(n == 0)
            return m;
        
        if(m == 0)
            return n;
        
        int[][] dp = new int[n + 1][m + 1];
        
        for(int j = 1; j < m + 1; j++)
            dp[0][j] = j;
        
        for(int i = 1; i < n + 1; i++)
            dp[i][0] = i;
        
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < m + 1; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j -1], dp[i - 1][j])) + 1;
            }
        }
        
        return dp[n][m];
    }
}