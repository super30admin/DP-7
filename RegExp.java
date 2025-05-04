// time:O(mn) space"O(mn)
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean [][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for(int j = 1; j <= n; j++){
            char pChar = p.charAt(j - 1);
            if (pChar== '*'){
                // only 0 case
                dp[0][j] = dp[0][j-2];
            }
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                }
                if(p.charAt(j-1) == '*' ){
                    // Zero occurrence
                    dp[i][j] = dp[i][j-2];
                    // One or more occurrence (if previous char matches current s)
                    if (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}

