// Time complexity: O(m * n)
// Space complexity: O(m * n) where m and n are lengths of String and Pattern.
class Solution {
    public boolean isMatch(String s, String p) {
        int pLen=p.length();
        int sLen=s.length();
        boolean[][] dp=new boolean[sLen+1][pLen+1];
        dp[0][0]=true;
        for(int i=1; i<=pLen; i++)
        {
            if(p.charAt(i-1)=='*')
                dp[0][i]=dp[0][i-2];
        }
        for(int i=1; i<=sLen; i++)
        {
            for(int j=1; j<=pLen; j++)
            {
                if(s.charAt(i-1)==p.charAt(j-1)|| p.charAt(j-1)=='.')
                    dp[i][j]=dp[i-1][j-1];
                else if (p.charAt(j-1)=='*')
                {
                    dp[i][j]=dp[i][j-2];
                    if(p.charAt(j-2)==s.charAt(i-1)||p.charAt(j-2)=='.')
                    {
                        if(dp[i][j-2]||dp[i-1][j])
                            dp[i][j]=true;
                    }
                }   
            }
        }
        return dp[sLen][pLen];
    }
}