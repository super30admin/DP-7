// Time Complexity : O(mn) m and n are length of string and pattern
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

class RegExMatching {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;
        int m = s.length();
        int n = p.length();
        boolean [][] dp = new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int j=1;j<=n;j++){
            if(p.charAt(j-1)=='*'){
                dp[0][j] = dp[0][j-2];
            }
        }



        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(p.charAt(j-1)=='*'){
                    if(s.charAt(i-1)==p.charAt(j-2) || p.charAt(j-2)=='.'){
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    }
                    else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
                else{
                    if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }

        return dp[m][n];

    }
}