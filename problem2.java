// Time Complexity :O(mn)
// Space Complexity :O(mn)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no

/*
Approach
We use DP tabulation to solve this one
"*" mean the char the cmae before "*" can be used mutiple time
"." this is a wild card, can be used as anything
we take dp of size m+1,n+1 +1 to accomodate for empty string
string as rows and pattern as columns

first we will the first row, we make 0,0 true, as empty string and make empty pattern
and if we encounter '*' we check what was the answer then the '*' char and put it there

then we start fill the table
there are 3 possible variation we need to account for, "*", "." or normal char

if we encounter '*' in the pattern
we have to option pick notpick or 1,0 case if the pervious char j-2 is matching i-1 or if j-2 is '.'
we take value or either i j-2 or i-1 j
if 0 case not using 
i j-2

if we hit '.' or normal char we take diagonal up value else flase
*/
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // top row
        for (int j = 1; j <= n; j++) {
            // check for '*' in
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // rest of dp
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 1
                if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        // 1 case
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }

                } else {
                    // regular char or '.'
                    if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                        // diagonal up
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[m][n];
    }
}