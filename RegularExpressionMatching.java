// TC: O(m*n) , m-> length of string, n-> length of pattern
// SC: O(m*n) , m-> length of string, n-> length of pattern
// Did it run successfully on Leetcode? : Yes
class Solution {
    public boolean isMatch(String s, String p) {
        if ( s == null || p == null)
            return false;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        // fill top row
        dp[0][0] = true;
        for ( int j = 1; j <= n ;j++)
        {
            if (p.charAt(j-1) == '*')
            {
                // 2 steps back, same row
                dp[0][j] = dp[0][j-2];
            }
        }
        
        for ( int i = 1; i <=m; i++)
        {
            for ( int j = 1; j <=n ;j++)
            {
                // if its a normal char (a-z)
                if (p.charAt(j-1) != '*')
                {
                    // if curr chars ARE SAME or curr char is a DOT
                   if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.')
                   {
                    // then check for prev chars ( up diagonal)
                    dp[i][j] = dp[i-1][j-1];
                   }
                }
                else
                {
                    // zero case
                       dp[i][j] =  dp[i][j-2];
                    
                    // one case -> if exixts ( precceding char should match) -> precceding char : p.charAt(j-2)
                    if (p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.' )
                    {
                        // only if curr char preceeding chars match, take value from above row
                        if (dp[i-1][j])
                          dp[i][j] = true;
                    }
                }      
            }   
        }
        return dp[m][n];
    }
}
