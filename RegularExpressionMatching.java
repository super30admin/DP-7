// Time Complexity : O(M*N)
// Space Complexity : O(M*N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class Solution {
    public boolean isMatch(String s, String p) {
        int m =s.length();
        int n = p.length();

        boolean dp[][] = new boolean[m+1][n+1];
        dp[0][0] = true;

        for(int j=1;j<=n;j++){
            char c = p.charAt(j-1);
            if(c == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                char in = p.charAt(j-1);

                if(in == '*'){
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    }else{
                        dp[i][j] = dp[i][j-2];
                    }
                }else{
                    if(in == s.charAt(i-1) || in == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }

        return dp[m][n];
    }
}