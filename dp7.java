//problem1
class Solution {
    public int minDistance(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        int[][] dp=new int[n+1][m+1];
        for(int i=0;i<=n;i++){
            dp[i][0]=i;
        }
        for(int j=0;j<=m;j++){
            dp[0][j]=j;
        }int in,de,re;
        int ans=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    //insert
                    in=dp[i][j-1];
                    //delete
                    de=dp[i-1][j];
                    //replace
                    re=dp[i-1][j-1];
                    dp[i][j]=1+Math.min(in,Math.min(de,re));
                }
            }
        }
        return dp[n][m];
    }
}
//problem2
class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int j = 2; j <= m; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if (pc == '*') {
                    dp[i][j] = dp[i][j - 2];
                    boolean match =
                        p.charAt(j - 2) == '.'
                        || p.charAt(j - 2) == sc;
                    if (match) {
                        dp[i][j] =
                            dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[n][m];
    }
}
