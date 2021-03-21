public class RegularExpressionMatching {
// Time Complexity : O(n^2)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : getting started
// Your code here along with comments explaining your approach
    class Solution {
        public boolean isMatch(String s, String p) {

            //create dp array and initialize 
            int m = s.length(), n = p.length();
            boolean dp[][] = new boolean[m+1][n+1];
            dp[0][0] = true;

            for (int i = 2; i <= n ; i++) {
                if(p.charAt(i-1) == '*'){
                    dp[0][i] = dp[0][i-2];
                }
            }

            //perform dp calculation
            for (int i = 1; i <= m ; i++) {
                for (int j = 1; j <=n ; j++) {
                    char sc = s.charAt(i-1);
                    char pc = p.charAt(j-1);

                    if(sc == pc || pc == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }else if(pc == '*'){
                        if(dp[i][j-2]){
                            dp[i][j] = true;
                        }else if(sc == p.charAt(j-2) || p.charAt(j-2) == '.'){
                            dp[i][j] = dp[i-1][j];
                        }
                    }

                }
            }
            return dp[m][n];
        }
    }
}
