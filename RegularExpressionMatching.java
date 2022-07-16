// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
public class RegularExpressionMatching {
    class Solution {
        public boolean isMatch(String s, String p) {
            if(".*".equals(p)) return true;

            int m = s.length();
            int n = p.length();

            boolean[][] dp = new boolean[m+1][n+1];
            dp[0][0] = true;
            //fill-up 1st row
            for(int j=1; j<=n; j++) {
                char c = p.charAt(j-1);
                if(c =='*') {
                    dp[0][j] = dp[0][j-2];
                }
            }

            for(int i=1; i<=m; i++) {
                char sChar = s.charAt(i-1);
                for(int j=1; j<=n; j++){
                    char pChar = p.charAt(j-1);
                    if(sChar == pChar || pChar == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    } else if (pChar == '*') {
                        dp[i][j] = dp[i][j-2];
                        if(p.charAt(j-2) == '.' || p.charAt(j-2) == sChar )
                            if( dp[i][j-2] || dp[i-1][j])
                                dp[i][j] = true;
                    }
                }
            }
            return dp[m][n];
        }
    }
}
