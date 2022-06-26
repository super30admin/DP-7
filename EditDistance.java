/*
 * TC = O(N*M)
 * SC = O(N*M)
 * 
 * Appraoch: At every subproblem, we try to turn s1 to s2 with insert, replace or delete operations.
 * If the characters are same, then we copy the previous subproblem's computed solution.
 */


import java.util.*;

public class EditDistance
{
    public static int minDistance(String s1, String s2)
    {
        if(s1 == null || s1.length() == 0) return 0;

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[m+1][n+1];

        for(int i=0;i<m+1;i++)
        {
            dp[i][0] = i;
        }

        for(int j =0;j<n+1;j++)
        {
            dp[0][j] = j;
        }

        for(int i = 1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(s1.charAt(j-1)==s2.charAt(i-1))
                {
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String args[])
    {
        System.out.println(minDistance("horse", "ros"));
    }
}