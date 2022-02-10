// Time Complexity: O(n*m) where n is length of s and m is length of p
// Space Complexity: O(n*m) where n is length of s and m is length of p
// DP Pattern 1
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null)
            return true;
        
        int n = s.length(), m = p.length();
        boolean dp[][] = new boolean[n+1][m+1]; // add extra space
        // empty string == empty string
        dp[0][0] = true;

        // first row match empty string to pattern
        for(int i = 1; i < dp[0].length; i++)
        {
            if(p.charAt(i-1) == '*') // only consider * case other chars are never going to match empty string
                dp[0][i] = dp[0][i-2]; // case 0 only, by selecting 1 case we will have to match chars to empty that will never happen
        }


        for(int i = 1 ; i < dp.length; i ++)
        {
            for(int j = 1; j < dp[0].length; j ++)
            {
                if(p.charAt(j-1) != '*')
                {
                    // chars have to match or char in pattern has to be dot
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
                    {
                        dp[i][j] = dp[i-1][j-1]; // we need to check if chars before curr also match
                    }
                }
                else
                {
                    // pattern was a *, create case 0 and case 1
                    dp[i][j] = dp[i][j-2]; // case 0
                    // case 1: previous chars need to match or has to be a dot
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.')
                    {
                        // can be either case 0 || case 1
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }

        return dp[n][m];
    }
}
