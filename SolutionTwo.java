// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :nopes

class SolutionTwo {
    public boolean isMatch(String s, String p)
    {
        int sl = s.length();
        int pl = p.length();

        boolean[][] dp = new boolean[sl+1][pl+1];

        dp[0][0] = true;

        for(int j=1;j<=pl;j++)
        {
            if(p.charAt(j-1)=='*')
            {
                dp[0][j] = dp[0][j-2];
            }
        }

        for(int i=1;i<dp.length;i++)
        {
            for(int j=1;j<dp[0].length;j++)
            {
                //normal charcter or dot
                if(p.charAt(j-1) != '*')
                {
                    if( p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1)=='.' )
                    {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                //* in the pattern
                else
                {
                    //choose zero case
                    dp[i][j] = dp[i][j-2];
                    //choose 1 case
                    if( p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-2) =='.')
                    {
                        if(dp[i-1][j])
                        {
                            dp[i][j] = true;
                        }
                    }
                }
            }

        }

        return dp[sl][pl];

    }
}