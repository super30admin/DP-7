// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :nopes

class Solution {
    public int minDistance(String word1, String word2)
    {
        int m = word2.length();
        int n = word1.length();

        int[][] dp = new int[m+1][n+1];

        for(int i=1;i<=n;i++)
        {
            dp[0][i]=i;
        }

        for(int i=1;i<=m;i++)
        {
            dp[i][0]=i;
        }

        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(word1.charAt(j-1)==word2.charAt(i-1))
                {
                    dp[i][j] = dp[i-1][j-1];
                }
                else
                {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]) );
                }

            }
        }

        return dp[m][n];
    }
}