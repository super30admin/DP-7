// Time Complexity : O(m * n) where m is the length of word1 and n is the length of word2
// Space Complexity : O(m * n)
// Did this code successfully run on Leetcode (72): Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(); int n = word2.length();
        int dp[][] = new int[m+1][n+1];
        
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
            }
        }
        return dp[m][n];
    }
}