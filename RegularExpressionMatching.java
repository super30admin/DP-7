class Solution {
    public boolean isMatch(String s, String p) {
        if(p == null) return s==null;
        int sl = s.length();
        int pl = p.length();
        boolean [][] dp = new boolean[sl+1][pl+1];
        dp[0][0] = true;
        for(int i = 1; i < dp[0].length; i++){
            if(p.charAt(i - 1) == '*'){
                dp[0][i] = dp[0][i-2];
            }
        }
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                //if characters are equal then assign diagonal value
                if(p.charAt(j - 1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                } 
                //if character in pattern is * then assign value from 2 columns backward
                else if (p.charAt(j - 1)  == '*'){
                    //0 condition 
                    dp[i][j] = dp[i][j-2];
                    //1 condition
                    if(p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1)){
                        if( dp[i][j-2] || dp[i-1][j]){
                             dp[i][j] = true;
                        }
                    }
                }
            }
        }
        return dp[sl][pl];
    }
}

//Time complexity : O(M*N) where M is string length and N is pattern length
//Space complexity : O(M*N) 
