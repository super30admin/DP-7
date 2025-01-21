//10. Regular Expression Matching - https://leetcode.com/problems/regular-expression-matching/description/
//Time Complexity: O(m*n)
//Space Complexity: O(m*n)

class Solution {
    public boolean isMatch(String s, String p) {
        //i: source, j: pattern
        //no choose: i, j+2
        //choose: i+1, j
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        //default 0th row is all false
        for(int j=1; j<=n; j++){
            char pChar = p.charAt(j-1); //1 prev row for the characters
            if(pChar == '*'){
                dp[0][j] = dp[0][j-2]; //first row - no choose case only
            }
        }

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                char pChar = p.charAt(j-1);
                if(pChar == '*'){ //either a star
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i][j-2];
                    }
                } else { //not a star
                    if(pChar == s.charAt(i-1) || pChar == '.'){ //incoming s char
                        dp[i][j] = dp[i-1][j-1];
                    } else{
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[m][n];
    }
}