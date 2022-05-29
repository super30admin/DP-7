// Time Complexity : O(nXm) n,m = lengths of word1 and word2
// Space Complexity : O(nXm) n,m = lengths of word1 and word2
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    public int minDistance(String word1, String word2) {

        int m = word1.length(); //rows of dp matrix
        int n = word2.length(); //cols of dp matrix

        int[][] dp = new int[m+1][n+1];

        //first row
        for(int j=1; j<dp[0].length; j++)
            dp[0][j] = j;

        //first col
        for(int i=1; i<dp.length; i++)
            dp[i][0] = i;

        //fill dp matrix
        for(int i=1; i<dp.length; i++)
        {
            for(int j=1; j<dp[0].length; j++)
            {
                //if chars match, result is diagonal up
                if(word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
