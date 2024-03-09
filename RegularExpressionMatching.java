/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(n^2)
* 
* Space Complexity: O(2n)
* 
*/

class RegularExpressionMatching {
    // private boolean helper(String s, String p, int i, int j, int[][] dp){
    // // base cases
    // if(j<0 && i<0){
    // return true;
    // }

    // // p exhausted
    // if(j<0){
    // return false;
    // }

    // // s exhausted
    // if(i<0){
    // boolean starFound = false;
    // while(j>=0){
    // if(p.charAt(j) == '*'){
    // starFound = true;
    // } else {
    // // char or . is found
    // if(starFound){
    // // balancing star with char or .
    // // representing 0 repetitions of char
    // starFound = false;
    // } else {
    // // cannot balance
    // return false;
    // }
    // }

    // j--;
    // }

    // return true;
    // }

    // if(dp[i][j]!=-1){
    // return dp[i][j] == 1;
    // }

    // if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
    // dp[i][j] = helper(s, p, i-1, j-1, dp) ? 1: 0;
    // } else if(p.charAt(j) == '*'){
    // // s: aa
    // // p:c*a*
    // // aa
    // // c*a

    // boolean matchCurrInS = false;
    // if(p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i)){
    // matchCurrInS = helper(s, p, i-1, j, dp);
    // }

    // // aa
    // // c*a*
    // boolean matchStartInP = helper(s, p, i, j-1, dp);

    // boolean zeroMatchInP = helper(s, p, i, j-2, dp);

    // dp[i][j] = (matchCurrInS || matchStartInP || zeroMatchInP) ? 1 : 0;
    // } else {
    // dp[i][j] = 0;
    // }

    // return dp[i][j] == 1;
    // }

    public boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }

        int m = s.length();

        int n = p.length();

        boolean[] prev = new boolean[n + 1];
        boolean[] curr = new boolean[n + 1];

        prev[0] = true;

        // first row matching s:"" with pattern
        for (int col = 2; col <= n; col++) {
            // zero match in p
            if (p.charAt(col - 1) == '*') {
                prev[col] = prev[col - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    curr[j] = prev[j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    // s: aa
                    // p:c*a*
                    // aa
                    // c*a

                    boolean matchCurrInS = false;
                    if (j - 2 >= 0 && (p.charAt(j - 2) == '.' ||
                            p.charAt(j - 2) == s.charAt(i - 1))) {
                        matchCurrInS = prev[j];
                    }

                    // aa
                    // c*a*
                    boolean matchStartInP = curr[j - 1];

                    boolean zeroMatchInP = false;

                    if (j - 2 >= 0) {
                        zeroMatchInP = curr[j - 2];
                    }

                    curr[j] = matchCurrInS || matchStartInP || zeroMatchInP;
                } else {
                    curr[j] = false;
                }
            }
            prev = curr.clone();
        }

        return prev[n];
    }
}