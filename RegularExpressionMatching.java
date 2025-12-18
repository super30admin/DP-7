// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Approach : This problem has repeated sub problems, so we need to implement DP. I have done it using a dp matrix with tabulation technique. We found a pattern
// that while parsing p string, when we encounter '*' we have an option of choosing or not choosing it to match with the character at s. So if the preceeding character
// in p matches with s or . is encountered, we check the 2 steps behind value and top value of the current i,j and if either one is true we assign true. Else
// we assign the value that is two steps behind. If we encounter a character in p, we check if it matches the character at s pointer or '.' then we assign the
// value that is on the top left diagonal position, else we assign false. Finally the last [m][n] in the matrix gives the result.

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1]; //dp matrix of m*n
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            char ch = p.charAt(i-1);
            if (ch == '*') {
                dp[0][i] = dp[0][i - 2]; //first row of dp matrix
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char ch = p.charAt(j-1);
                if(ch == '*'){ //when * is encountered
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){ //when preceding character in p matches with s or . is encountered
                        dp[i][j] = dp[i][j-2] || dp[i-1][j]; //OR condition of 2 steps behind value, top value
                    }else{
                        dp[i][j] = dp[i][j-2]; //2 steps behind value
                    }
                }else{
                    if(ch == s.charAt(i-1) || ch == '.'){ //when character at p and s pointer matches or . is encountered
                        dp[i][j] = dp[i-1][j-1]; //left up diagonal value
                    }else{
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[m][n];
    }
}