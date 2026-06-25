
// Time Complexity : O(m*n) where m is the length of the string and n is the length of the pattern
// Space Complexity : O(m*n) where m is the length of the string and n is the length of the pattern
// Did this code successfully run on Leetcode : YES 
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// Approach: We will use a 2D boolean array to store the results of subproblems.
// First we fill it with -1
// Then we use a recursive helper function to calculate the minimum edit distance between the two strings.
// The base cases handle when one of the strings is empty, returning the length of the other string as the number of operations needed.
// If the characters at the current indices match, we move to the next indices in both strings
// If they don't match, we consider three operations: add, edit, and delete, and take the minimum of these three operations plus one for the current operation.
// Finally, we store the result in the dp array and return it. The final result will be stored in dp[0][0], which indicates the minimum edit distance between the two strings.  

import java.util.Arrays;

class Solution {

    int[][] dp;

    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], -1);
        }

        return helper(word1, word2, 0, 0);
    }

    private int helper(String word1, String word2, int i, int j) {

        if (i == word1.length()) {
            return word2.length() - j;
        }

        if (j == word2.length()) {
            return word1.length() - i;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int result;

        if (word1.charAt(i) == word2.charAt(j)) {

            result = helper(word1, word2, i + 1, j + 1);

        } else {

            int add = 1 + helper(word1, word2, i + 1, j);

            int edit = 1 + helper(word1, word2, i + 1, j + 1);

            int delete = 1 + helper(word1, word2, i, j + 1);

            result = Math.min(add, Math.min(edit, delete));
        }

        dp[i][j] = result;

        return result;
    }
}