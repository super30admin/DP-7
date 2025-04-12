// Problem2 Regular Expression Matching (https://leetcode.com/problems/regular-expression-matching/)

// Time Complexity : O(m*n) 
// Space Complexity : O(m*n) , optimized-o(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach in three sentences only
/*
 * Here, have a dummy row and col for empty string and when moving forward, if pchar != * and schar ==pchar or pchar ==* then fill what is just
 * above else false. If pchar is * then if schar == pchar at j-2 th index or j-2th index is * then or operation of element at 2steps back and
 * doagonal up element else take element at 2 steps back. Finally return the last element.
 */
// 1
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean [][]dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int i = 1; i<=n; i++){
            char pChar = p.charAt(i-1);
            if(pChar == '*'){
                dp[0][i] = dp[0][i-2];
            }
        }
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                char sChar = s.charAt(i-1);
                char pChar = p.charAt(j-1);
                if(pChar != '*'){
                    // normal char or a dot
                    if(pChar == sChar || pChar == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else{
                        dp[i][j] = false;
                    }
                }
                else{
                    //its a *
                    // 0 case
                    dp[i][j] = dp[i][j-2];
                    // if 1 case is available
                    // curr char of source matches with preceeding char of pattern
                    if(sChar == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i-1][j] || dp[i][j-2];
                    }
                    else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
// 2
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[]dp = new boolean[n+1];
        dp[0] = true;
        for(int i = 1; i<=n; i++){
            char pChar = p.charAt(i-1);
            if(pChar == '*'){
                dp[i] = dp[i-2];
            }
        }
        for(int i = 1; i <= m; i++){
            boolean diagUp = dp[0];
            dp[0] = false;
            for(int j = 1; j <= n; j++){
                char sChar = s.charAt(i-1);
                char pChar = p.charAt(j-1);
                boolean temp = dp[j];
                if(pChar != '*'){
                    // normal char or a dot
                    if(pChar == sChar || pChar == '.'){
                        dp[j] = diagUp;
                    }
                    else{
                        dp[j] = false;
                    }
                }
                else{
                    // if 1 case is available
                    // curr char of source matches with preceeding char of pattern
                    if(sChar == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        dp[j] = dp[j] || dp[j-2];
                    }
                    else{
                        dp[j] = dp[j-2];
                    }
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
}