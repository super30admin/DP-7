//Time = O(mn), m and n are the lengths of the two input strings
//Space = O(mn), need to create a table of that size to store the edit distances between all pairs of prefixes of the two input strings.

class Solution {
    public int minDistance(String word1, String word2) {
        // get the lengths of the two words
        int m = word1.length();
        int n = word2.length();
        
        // create a 2D table to store the edit distances between the two words
        int[][] dp = new int[m+1][n+1];
        
        // fill in the base cases where one of the strings is empty
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        
        // fill in the rest of the table using dynamic programming
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // if the two characters are the same, no operation is needed
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // if the characters are different, we need to choose the operation with the minimum cost
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], // replace
                                            Math.min(dp[i-1][j], // delete
                                                     dp[i][j-1])); // insert
                }
            }
        }
        
        // the edit distance is in the bottom-right corner of the table
        return dp[m][n];
    }
}
