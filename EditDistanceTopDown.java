/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(n^2)
* 
* Space Complexity: O(n^2)
* 
*/

public class EditDistanceTopDown {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }

        int m = word1.length();

        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int index = 0; index <= n; index++) {
            // word1 exhausted or empty
            dp[0][index] = index;
        }

        for (int index = 0; index <= m; index++) {
            // word2 exhausted or empty
            dp[index][0] = index;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // operations
                    // add
                    int add = 1 + dp[i][j - 1];

                    // delete
                    int delete = 1 + dp[i - 1][j];

                    // update
                    int update = 1 + dp[i - 1][j - 1];

                    dp[i][j] = Math.min(Math.min(add, delete), update);
                }
            }
        }

        return dp[m][n];
    }
}