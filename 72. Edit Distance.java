// Time Complexity : O(n * m)
// Space Complexity : O(n * m)
// Method used : DP

class Solution {

    public int minDistance(String word1, String word2) {

        int n = word1.length(), m = word2.length();

        int[][] dp = new int[n + 1][m + 1];

        // This means index2 is not < 0. So we need index2 + 1 insertions to convert word1 to word2
        for(int j = 0; j < m + 1; j++) dp[0][j] = j + 1;

        // This means index1 is not < 0. So we need index1 + 1 deletions to convert word1 to word2
        for(int i = 0; i < n + 1; i++) dp[i][0] = i + 1;

        for(int i = 1; i < n + 1; i++)
        {
            for(int j = 1; j < m + 1; j++)
            {
                // If both characters are same it is not required to perform any operation
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];

                else
                {
                    // Case 1 : Insert operation
                    int insert = 1 + dp[i][j - 1];

                    // Case 2 : Delete operation
                    int delete = 1 + dp[i - 1][j];

                    // Case 3 : Replace operation
                    int replace = 1 + dp[i - 1][j - 1];

                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        return dp[n][m] - 1;
    }
}
