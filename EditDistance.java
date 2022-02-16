// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// we use dp to solve this
// We check if the character in the words is same then we take the diagonal
// else we take 1 + the min of the up left and diagonal

class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2))
            return 0;

        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;

        }

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;

        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
            }
        }
        return dp[m][n];
    }
}

// Time Complexity : O(mn)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// we use dp to solve this
// same as above but we use one dimensional array for this
// We check if the character in the words is same then we take the diagonal
// else we take 1 + the min of the up left and diagonal

class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2))
            return 0;

        int m = word1.length();
        int n = word2.length();
        if (m > n)
            return minDistance(word2, word1);
        int[] dp = new int[m + 1];

        for (int j = 0; j <= m; j++) {
            dp[j] = j;

        }

        for (int i = 1; i <= n; i++) {
            int temp = dp[0];
            dp[0] = i;
            for (int j = 1; j <= m; j++) {
                int prev = dp[j];
                if (word2.charAt(i - 1) == word1.charAt(j - 1)) {
                    dp[j] = temp;
                } else
                    dp[j] = 1 + Math.min(dp[j], Math.min(dp[j - 1], temp));

                temp = prev;
            }
        }
        return dp[m];
    }
}