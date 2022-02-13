// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// This is a DP problem, where we choose from 3 operations: add, delete and update
// add - left
// delete - top
// update - diagonal
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        if (word1.equals(word2)) return 0;

        int m=word1.length();
        int n=word2.length();

        // let m be always greater than n, therefore word1 will be greater always
        // This logic is not very useful here but when we optimize for space by using a single dimentional array, this will be useful
        if (m < n) minDistance(word2, word1);

        // longer word will be the row
        int[][] dp = new int[m+1][n+1];

        // first row
        for (int i=0; i<=n; i++) {
            dp[0][i] = i;
        }

        // first col
        for (int i=0; i<=m; i++) {
            dp[i][0] = i;
        }

        // rest of the dp array
        for (int i=1; i<=m; i++) {      //row
            for (int j=1; j<=n; j++) {  //col
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                }
            }
        }

        return dp[m][n];
    }
}

// ---------------------- Space Optimized Approach ----------------------

// Time Complexity : O(m*n)
// Space Complexity : O(n), where n is the length of smaller word
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// This is a DP problem, where we choose from 3 operations: add, delete and update
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        if (word1.equals(word2)) return 0;

        int m=word1.length();
        int n=word2.length();

        // let m be always greater than n, therefore word1 will be greater always
        if (m < n) minDistance(word2, word1);

        // longer word will be the row
        int[] dp = new int[n+1];

        // first row
        for (int i=0; i<=n; i++) {
            dp[i] = i;
        }

        // rest of the dp array
        for (int i=1; i<=m; i++) {      //traverse the larger string, row wise
            int temp = dp[0];
            dp[0] = i;  // filling the first element for each row by i value which will go as 0, 1, 2, ...
            for (int j=1; j<=n; j++) {  //col
                int prev = dp[j];
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[j] = temp;
                }
                else {
                    dp[j] = Math.min(Math.min(dp[j], dp[j-1]), temp) + 1;
                }
                temp = prev;
            }
        }

        return dp[n];
    }
}