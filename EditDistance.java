class Solution {
    public int minDistance(String word1, String word2) {
        // a. 2-d DP 
        // int m = word1.length();
        // int n = word2.length();

        // int[][] dp = new int[m+1][n+1];

        // for(int i = 0; i <= n; i++)
        // {
        //     dp[0][i] = i;
        // }

        // for(int i = 1; i <= m; i++)
        // {
        //     for(int j = 0; j <= n; j++)
        //     {
        //         if(0 == j)
        //         {
        //             dp[i][j] = i;
        //         }
        //         else
        //         {
        //             if(word1.charAt(i-1) == word2.charAt(j-1))
        //             {
        //                 dp[i][j] = dp[i-1][j-1];
        //             }
        //             else
        //             {
        //                 dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j-1], dp[i-1][j]))+1;
        //             }
        //         }
        //     }
        // }

        // return dp[m][n];

        // b. 1-d DP 
        int m = word1.length();
        int n = word2.length();

        int[] dp = new int[n+1];

        for(int i = 0; i <= n; i++)
        {
            dp[i] = i;
        }

        int diagonalUp = dp[0];
        for(int i = 1; i <= m; i++)
        {
            for(int j = 0; j <=n; j++)
            {
                int temp = dp[j];
                if(0 == j)
                {
                    dp[j] = i;
                }
                else
                {
                    if(word1.charAt(i-1) == word2.charAt(j-1))
                    {
                        dp[j] = diagonalUp;
                    }
                    else
                    {
                        dp[j] = Math.min(dp[j-1], Math.min(diagonalUp, dp[j]))+1;
                    }
                }
                diagonalUp = temp;
            }
        }
        return dp[n];
    }
}