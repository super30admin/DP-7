//TC = O(m*n) going through dp array
//SC = O(m*n) for DP
// Was able to run in LeetCode = yes

package com.madhurima;

public class RegularExpressionMatching {
}

class RegularExpressionMatchingDP {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)){
            return true;
        }

        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];

        dp[0][0] = true; //empty string matches empty string

        for(int j = 1; j < n+1; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2]; //two steps back
            }
        }

        for(int i = 1; i < m+1; i++){
            for(int j = 1; j < n+1; j++){ //first column by default false, empty string won't match anything else
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){ //solved earlier, ans lies in diagonal
                        dp[i][j] = dp[i-1][j-1];
                    }
                }else{ //asterix
                    //zero condition
                    dp[i][j] = dp[i][j-2]; //two step back
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){ //checking char prior to *
                        dp[i][j] = dp[i][j] || dp[i-1][j]; //just above
                    }
                }
            }
        }


        return dp[m][n];


    }
}
