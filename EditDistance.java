// TC: O(m*n) , m-> length of word2, n-> length of word1
// SC: O(m*n) , m-> length of word2, n-> length of word1
// Did it run successfully on Leetcode? : Yes
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word2.length();
        int n = word1.length();
        int[][] dp = new int[m+1][n+1];
        // fill first row
        for ( int j = 0; j <= n; j++)
        {
            dp[0][j] = j;
            
        }
         //  fill first column
        for ( int i = 0; i <= m; i++)
        {
            dp[i][0] = i;    
        }
        // fill rest of the dp matrix
        for ( int i = 1; i <= m; i++)
        {
            for ( int j = 1; j <= n; j++)
            {
                // if current chars are same, copy up diagnal value
                if (word1.charAt(j-1) == word2.charAt(i-1))
                {                    
                     dp[i][j] = dp[i-1][j-1];
                }
                else // if current chars are NOT SAME,
                {
                    // 1 + min(delete -> one left, update -> up diagnal, add-> prev row val)
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                }
            }
        }
        return dp[m][n];
    }
}
