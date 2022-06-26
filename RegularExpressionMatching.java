/*
 * TC = O(N*M)
 * SC = O(N*M)
 * 
 * Approach: Whenever we have a * in our pattern, we have two possibilities - choose the prev char
 * or to not choose. If we decide to choose, we take answer from prev computed that lies on top,
 * if not we look at two steps behind.
 */

import java.util.*;
public class RegularExpressionMatching {
    

    public static boolean isMatch(String s, String p)
    {
        if(s.equals(p)) return true;

        int n = s.length();
        int m =p.length();

        boolean[][] dp = new boolean[n+1][m+1];

        dp[0][0] = true;
        for(int i = 1;i<=m;i++)
        {
            if(p.charAt(i-1)=='*')
            {
                dp[0][i] = dp[0][i-2]; 
            }
        }

        for(int i = 1;i<=n;i++)
        {
            for(int j = 1;j<=m;j++)
            {
                if(p.charAt(j-1)!='*')
                {
                    if(p.charAt(j-1)==s.charAt(i-1) || p.charAt(j-1)=='.')
                    {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else{

                    dp[i][j] = dp[i][j-2];

                    if(p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-1)=='.')
                    {
                        dp[i][j] = dp[i][j] || dp[i][j-1];
                    }
                }
            }
        }

        return dp[n][m];

    }

    public static void main(String args[])
    {
        String s = "aa";
        String p = "a";

        System.out.println(isMatch(s, p));

    }
}

