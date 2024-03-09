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
import java.util.Arrays;

public class EditDistanceBottomUp {
    private int helper(String word1, String word2, int i, int j, int[][] dp) {
        // base cases
        if (i < 0 && j < 0) {
            return 0;
        }

        if (i < 0) {
            // add all chars of word2
            return j + 1;
        }

        if (j < 0) {
            // remove all chars of word1
            return i + 1;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // no change
        if (word1.charAt(i) == word2.charAt(j)) {
            dp[i][j] = helper(word1, word2, i - 1, j - 1, dp);
        } else {
            // operations
            // add
            int add = 1 + helper(word1, word2, i, j - 1, dp);

            // delete
            int delete = 1 + helper(word1, word2, i - 1, j, dp);

            // update
            int update = 1 + helper(word1, word2, i - 1, j - 1, dp);

            dp[i][j] = Math.min(Math.min(add, delete), update);
        }

        return dp[i][j];
    }

    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }

        int m = word1.length();

        int n = word2.length();

        int[][] dp = new int[m][n];

        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        return helper(word1, word2, m - 1, n - 1, dp);
    }
}
