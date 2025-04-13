//We implemented this using bottom-up DP by making use of tabulation
//We take an additional row and column to match for empty strings
//At each index in table, we check if the current character in pattern is not a *, then we check if it matches with source char or if pChar is a '.', if any of them are true, we put diagonal up left value, else we put false
//If the current character in pattern is a '*', then we have 2 cases, whether we choose it or not. We put the value accordingly
//Time Complexity: O(m*n) where m and n are lengths of s and p strings
//Space Complexity: O(m*n)
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int j = 1; j<=n; j++){
            char pChar = p.charAt(j-1);
            if(pChar == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        for(int i = 1; i<=m; i++){
            for(int j = 1; j <=n; j++){
                char sChar = s.charAt(i-1);
                char pChar = p.charAt(j-1);
                if(pChar != '*'){
                    if(sChar == pChar || pChar == '.'){
                        dp[i][j] = dp[i-1][j-1]; //diagonal up left
                    } else{
                        dp[i][j] = false;
                    }
                } else {
                    //*
                    //zero case
                    
                    //one case
                    //curr char of source matches with preceding char of pattern
                    if(sChar == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i-1][j] || dp[i][j-2];
                    } else {
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        return dp[m][n];
    }
}