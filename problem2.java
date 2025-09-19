public class problem2 {
// Time Complexity= O(m*n)
// Space Complexity = O(m*n)

    //Explanation- The code solves using DP to check if string matches a pattern wit. If letter match , we look diagnol and
    // to check if previous string match and if we see *, we either skip 2 steps back or take from straight above, treat as repeat

    public boolean isMatch(String s, String p) {
        int m= s.length();
        int n=p.length();
        boolean[][] dp= new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int j=1;j<=n;j++){
            if(p.charAt(j-1)=='*'){
                dp[0][j]=dp[0][j-2];
            }
        }

        for(int j=1;j<=n;j++)
        {
            char pChar= p.charAt(j-1);
            if (pChar=='*')
            {
                dp[0][j]=dp[0][j-2];
            }
        }
        for (int i=1;i<=m;i++)
        {
            for (int j=1;j<=n;j++)
            {
                char pChar= p.charAt(j-1);
                if (pChar=='*')
                {
                    if (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2)=='.')
                    {
                        dp[i][j] =dp[i][j-2] || dp[i-1][j];
                    }
                    else {
                        dp[i][j]= dp[i][j-2];
                    }
                }
                else {
                    if (pChar == s.charAt(i-1) || pChar=='.')
                    {
                        dp[i][j] =dp[i-1][j-1];
                    }
                    else {
                        dp[i][j]=false;
                    }
                }
            }
        }
        return dp[m][n];
    }
}
