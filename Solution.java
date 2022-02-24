class Solution {

    //o(m*n) time and o(m*n)space
    public boolean isMatch(String s, String p) {
        int sl = s.length(); int pl = p.length();
        if(s.equals(p)) return true;
        boolean[][] dp = new boolean[sl + 1][pl + 1];
        dp[0][0] = true;

        //top row
        for(int j = 1; j < dp[0].length; j++){
            if(p.charAt(j - 1) == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(p.charAt(j - 1) != '*'){
                    if(p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j-1) == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                } else {
                    //zero case
                    dp[i][j] = dp[i][j-2];
                    //one case if available
                    if(s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }


        return dp[sl][pl];

    }

    //o(m*n) time and o(m*n)space
        public int minDistance(String word1, String word2) {
            int n = word1.length();
            int m = word2.length();
            int[][] dp = new int[m+1][n+1];

            //top row
            for(int j = 0; j < dp[0].length; j++){
                dp[0][j] = j;

            }
            //top col
            for(int i = 0; i < dp.length; i++){
                dp[i][0] = i;

            }

            for(int i = 1; i <= m; i++){
                for(int j = 1; j <= n;j++){
                    if(word1.charAt(j-1) == word2.charAt(i-1)){
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                    }
                }
            }
            return dp[m][n];
        }

}