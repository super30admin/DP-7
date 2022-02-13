// Time complexity = O(m*n)
// Space complexity = O(m*n)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach

// We solve this question using DP with choose not choose case.
// the rows is the string s and coloumns are p
// We start with empty in row and col, and True in the dp array at index 0,0
// We traverse row wise and have only 'not choose' case, so we get the value from two spaces back
// For the rest of the dp array, when we have a * we evaluate the expression, if the previous element to * match with row, we have both 'choose' [above value] and 'not choose' [2 steps back value] cases available, so we do a or case.
// If the left to * doesnt match the row element, we only have 'no choose' case [2 step back]
// If row and coloumn element match, we fetch the value from the diagonal element
class Solution {
    public boolean isMatch(String s, String p) {
        if (s==null || s.length() == 0) return false;
        if (p==null || p.length() == 0 || s.equals(p)) return true;

        int n = s.length();
        int m = p.length();
        char dot = '.';

        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0]=true;  //matching "" to ""

        // first row
        for (int j=1; j < dp[0].length; j++) {
            if (p.charAt(j-1) == '*') {
                // we only have no choose case so we get the value from 2 steps back
                dp[0][j] = dp[0][j-2];
            }
        }

        // Note: col 0 will always be false

        for (int i=1; i < dp.length; i++) {
            for (int j=1; j < dp[0].length; j++) {
                char sChar = s.charAt(i-1);
                char pChar = p.charAt(j-1);

                // Note: We evaluate the conditions on a *
                if (pChar != '*') {
                    if (pChar == sChar || pChar == dot) {
                        // both the characters match
                        // get the value from diagonal element
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else {
                    // This is the * case, we have 2 options,
                    // case 1: the preceding char matches - we have 0 and 1 case available
                    // case 2: the preceding char doesn't matches - we have 0 case available

                    dp[i][j] = dp[i][j-2];                                      // case 2
                    if (p.charAt(j-2) == sChar || p.charAt(j-2) == dot) {       // case 1
                        // we have both 'choose' and 'not choose' cases
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }

        return dp[n][m];
    }
}