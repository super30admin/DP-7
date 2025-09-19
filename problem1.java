public class problem1 {
// Time Complexity= O(m*n)
// Space Complexity = O(m*n)

    //Explanation- The code solves using DP to calculate min distance such that above cell gives the add operation, up-left gives the edit operation
    // left gives the delete operation and diagnol also gives the no-operation


    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m+1][n+1];
        for (int j=1;j<=n;j++)
        {
            dp[0][j]= j;
        }
        for (int i=1;i<=m;i++)
        {
            for (int j=0;j<=n;j++)
            {
                if (j==0)
                {
                    dp[i][j]= i;
                }
                else {
                    if (word1.charAt(i-1) == word2.charAt(j-1))
                    {
                        dp[i][j]= dp[i-1][j-1]; //no operation
                    }
                    else {
                        dp[i][j]= 1+Math.min(dp[i-1][j],Math.min(dp[i-1][j-1], dp[i][j-1]));
                    }
                }
            }
        }
        return dp[m][n];
    }

}
