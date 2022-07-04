//time-O(m*n), space - O(m*n)
class Solution {
    public boolean isMatch(String s, String p) {
        if(s==null || p==null || s.length()==0 || p.length()==0) return false;

        int m = s.length()+1;
        int n = p.length()+1;

        boolean[][] dp = new boolean[m][n];

        dp[0][0] = true;

        for(int j=1; j<n; j++){
            if(p.charAt(j-1)=='*'){
                dp[0][j] = dp[0][j-2];
            }
        }

        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(p.charAt(j-1)!='*'){
                    if(p.charAt(j-1)==s.charAt(i-1) || p.charAt(j-1)=='.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else{
                    if(p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-2)=='.'){
                        //          0 case         1 case
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    }
                    else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }

        return dp[m-1][n-1];
    }
}