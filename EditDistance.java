// Time Complexity : O(N)
// Space Complexity : O(1s)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach


//Using bottom up - tabulation - optimising space
class Solution {
    int m;
    int n;

    public int minDistance(String word1, String word2) {
        this.m = word1.length();
        this.n = word2.length();
        int[]dp = new int[n + 1];
        // fill first row
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }
        for (int i = 1; i <= m; i++) {
            int diagUp = dp[0];
            dp[0] = i;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = diagUp; // fill with diagonal
                } else {
                    // take min from Insertion, deletion and replace
                    dp[j] = 1 + Math.min(dp[j - 1], Math.min(diagUp, dp[j]));
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
}