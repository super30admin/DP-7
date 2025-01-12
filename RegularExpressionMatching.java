// Approach : DP
// Time : O(sl*pl);
// Space : O(sl*pl);
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();

        boolean[][] dp = new boolean[sl + 1][pl + 1];
        dp[0][0] = true;

        // fill the top row - 1 to n (columns)
        // go through all dp columns
        for (int i = 1; i < dp[0].length; i++) {
            // check for * in the pattern
            // in the first row we will only have 0 case
            if (p.charAt(i - 1) == '*') {// taken one charcter extra
                // zero case - Go two steps back (2 columns)
                dp[0][i] = dp[0][i - 2];
            }
        }

        // First column - 1 to m (rows) is always false and by default dp is initialized
        // to false. so no need to manually fill in

        // iterate through the dp - rows and columns
        for (int i = 1; i < dp.length; i++) { // word
            for (int j = 1; j < dp[0].length; j++) { // pattern
                if (p.charAt(j - 1) != '*') {
                    // implies either it is a character or '.'
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        // take it from diagonal
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        // by default is also false - no need
                        dp[i][j] = false;
                    }

                } else {
                    // zero and one cases are only available if you have *
                    dp[i][j] = dp[i][j - 2]; // zero case - take two steps back
                    // check if one case is available or not
                    // when you are on * .. character before * matches at character at s
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j]; // one case - directly from the above 
                    }
                }
            }
        }

        return dp[sl][pl];
    }
}