class regularExprressionMatching {
    public boolean isMatch(String s, String p) {
        int m= s.length();
        int n= p.length();
        boolean [][]dp = new boolean[m+1][n+1];
        dp[0][0]=true;

        for(int j=1;j<=n;j++)
        {
            char pchar = p.charAt(j-1);
            if(pchar =='*')
            {
                //only 0 case
                dp[0][j] = dp[0][j-2];

            }
        } 

        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
                char schar = s.charAt(i-1);
                char pchar = p.charAt(j-1);
            if(pchar !='*')
            {
                //normal char or .
                if(pchar==schar || pchar=='.')
                {
                    dp[i][j]=dp[i-1][j-1];
                }
                else{
                     dp[i][j] = false;
                }
            }
            else{
                //if it is *
                //zero case
                dp[i][j] = dp[i][j-2];
                //one case if it is available
                //cur char of src matches with preceeding char of pattern
                if(schar == p.charAt(j-2) || p.charAt(j-2) =='.')
                {
                    dp[i][j] = dp[i-1][j] || dp[i][j-2];
                }
                else{
                     dp[i][j] = dp[i][j-2];
                }

            }

            }
        }
        return dp[m][n];
    }
}

//tc: O(m*n)
//sc: O(m*n)