class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null){
            return false;
        }
        
        int slen = s.length();
        int plen = p.length();
        
        boolean[][] dp = new boolean[slen+1][plen+1];
        dp[0][0] = true;
        
        for(int i = 1; i < dp[0].length; i++){
            if(p.charAt(i-1) == '*'){
                dp[0][i] = dp[0][i-2];
            }
        }
        
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i][j-2];
                    if(p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1)){
                        if(dp[i][j-2] || dp[i-1][j]){
                            dp[i][j] = true;
                        }
                    }
            }
        }
    }
        return dp[slen][plen];
    }
}