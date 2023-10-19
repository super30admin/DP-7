//TC = O(m*n)
//SC = O(m*n)

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        //top row
        for(int j = 1; j<=n ; j++){
            char c = p.charAt(j-1);
            if(c == "*"){
                dp[0][j] = dp[0][j-2];
            }
        } 
        //rest of matrix
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                char pchar = p.charAt(j-1);
                if(pchar != '*'){
                    //normal char || it is a dot
                    if(pchar == '.' || pchar == s.charAt(i-1)){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }else{
                    if(p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-1) == '.'){
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    }else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
return dp[m][n];
    }
}