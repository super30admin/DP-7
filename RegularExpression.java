//TC will be O(m * n)
//SC will be O(m * n)

class RegularExpression {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)){
            return true;
        }

        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for(int j =1; j < n+1; j++){
            if(p.charAt(j - 1)== '*'){
                dp[0][j] = dp[0][j -2];
            }
        }

        for(int i = 1; i < m+1; i++){
            for(int j =1; j < n + 1; j++){
                if(p.charAt(j -1) == s.charAt(i -1) || p.charAt(j -1) == '.'){
                    dp[i][j] = dp[i -1][j -1];
                }
                else if(p.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i][j -2];
                    if(s.charAt(i -1) == p.charAt(j -2) || p.charAt(j -2) == '.'){
                        dp[i][j] = dp[i][j] || dp[i -1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args){
        RegularExpression obj = new RegularExpression();
        String s = "aa", p = "a";
        System.out.println(obj.isMatch(s,p));

    }
}