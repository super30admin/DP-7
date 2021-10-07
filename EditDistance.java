//https://leetcode.com/problems/edit-distance/
/*
Time: O(m*n) where m=word1.length(), n=word2.length()
Space: O(m*n) for the dp[][]
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/
public class EditDistance {
    public int minDistance(String word1, String word2) {

        if (word1.equals(word2))
            return 0;

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // fill first col with index val
        for (int i = 0; i <= m; i++)
            dp[i][0] = i;

        // fill first row with index val
        for (int j = 0; j <= n; j++)
            dp[0][j] = j;

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) // if they are equal, just take diagonal
                    dp[i][j] = dp[i - 1][j - 1];

                else
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1; // min of all three
                                                                                                     // vals + 1
            }
        }

        return dp[m][n];
    }

}
