class Solution {
    //tc-o(mn)sc-o(mn)
    public boolean isMatch(String s, String p) {
        int s1 = s.length();
        int p1 = p.length();

        boolean[][]dp = new boolean[s1+1][p1+1];
        dp[0][0] = true;
        //first row
        for(int j=1;j<dp[0].length;j++)
        {
            if(p.charAt(j-1) == '*')
            {
            dp[0][j] = dp[0][j-2];
            }
        }
        for(int i=1;i<dp.length;i++)
        {
            for(int j=1;j<dp[0].length;j++)
            {
                //normal
                if(p.charAt(j-1) != '*'){
                    //same letter or .
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.')
                    {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else
                //if it is * 
                { //zero
                dp[i][j] = dp[i][j-2];
                //one case
                if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.')//compare letter before *
                {
                    dp[i][j] = dp[i][j] || dp[i-1][j];
                }

                }
                


            }
        }
        

       return dp[dp.length-1][dp[0].length-1];


    }
}