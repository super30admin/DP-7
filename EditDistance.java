//Time Complexity: O(n)
//Space Complexity: O(n)

import java.util.*;

class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for(int[] arr : dp) Arrays.fill(arr, -1);
        return helper(word1, word2, word1.length() - 1, word2.length() - 1, dp);
    }

    private int helper(String word1, String word2, int i, int j, int[][] dp) {
        if(i < 0) return j + 1;
        if(j < 0) return i + 1;

        if(dp[i][j] != -1) return dp[i][j];

        int ans = Integer.MAX_VALUE;
        if(word1.charAt(i) == word2.charAt(j)) {
            return helper(word1, word2, i-1, j-1, dp);
        } else {
            // insert
            ans = Math.min(1 + helper(word1, word2, i, j-1, dp), ans);
            // delete
            ans = Math.min(1 + helper(word1, word2, i-1, j, dp), ans);
            // replace
            ans = Math.min(1 + helper(word1, word2, i-1, j-1, dp), ans);
        }
        return dp[i][j] = ans;
    }
}