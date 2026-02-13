class Solution {
    public boolean isMatch(String s, String p) {
        // a. 2-D DP
        // int m = s.length();
        // int n = p.length();

        // boolean[][] dp = new boolean[m+1][n+1];
        // // empty string matches empty string 
        // dp[0][0] = true;

        // for(int j = 1; j <= n; j++)
        // {
        //     char pChar = p.charAt(j - 1);
        //     if(pChar == '*')
        //     {
        //         dp[0][j] = dp[0][j-2];
        //     }
        // }

        // for(int i = 1; i <= m; i++)
        // {
        //     for(int j = 1; j <= n; j++)
        //     {
        //         char pChar = p.charAt(j - 1);
        //         if(pChar == '*')
        //         {
        //             if(p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j-2) == '.')
        //             {
        //                 dp[i][j] = dp[i][j-2] || dp[i-1][j];
        //             }
        //             else
        //             {
        //                 dp[i][j] = dp[i][j-2];
        //             }
        //         }
        //         else
        //         {
        //             if(pChar == s.charAt(i-1) || pChar == '.')
        //             {
        //                 dp[i][j] = dp[i-1][j-1];
        //             }
        //             else
        //             {
        //                 dp[i][j] = false;
        //             }
        //         }
        //     }
        // }
        // return dp[m][n];

        // b. 1-D DP
        int m = s.length();
        int n = p.length();

        boolean[] dp = new boolean[n+1];

        dp[0] = true; // empty string

        for(int j = 1; j <= n; j++)
        {
            char pChar = p.charAt(j - 1);
            if(pChar == '*')
            {
                dp[j] = dp[j-2];
            }
        }

        for(int i = 1; i <= m; i++)
        {
            boolean diagonalUp = dp[0];
            dp[0] = false; // ensuring it is set to false deafult for up coming row calculation
            for(int j = 1; j <= n; j++)
            {
                boolean temp = dp[j];
                char pChar = p.charAt(j-1);
                if(pChar == '*')
                {
                    if(p.charAt(j - 2) == s.charAt(i-1) || p.charAt(j - 2) == '.')
                    {
                        dp[j] = dp[j-2] || dp[j];
                    }
                    else
                    {
                        dp[j] = dp[j-2];
                    }
                }
                else
                {
                    if(pChar == s.charAt(i-1) || pChar == '.')
                    {
                        dp[j] = diagonalUp;
                    }
                    else
                    {
                        dp[j] = false;
                    }
                }
                diagonalUp = temp;
            }
        }

        return dp[n];
    }
}