public class Regex {
     //o(mn)
     public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean dp[][] = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int j = 1; j <= n; j++){
            char pChar = p.charAt(j-1);
            if(pChar == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                char pChar = p.charAt(j-1);
                if(pChar == '*'){
                    //choose 
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    }
                    else{ //no choose 
                        dp[i][j] = dp[i][j-2];
                    }

                }else{
                    if(s.charAt(i-1) == pChar || pChar == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }else{
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[m][n];
    }
}
