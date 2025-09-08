// If we draw a tree for c*a*b - we solve repeated sub probs hence DP.
// * handling is the tricky part → always think “zero occurrence OR one more occurrence.”
class Solution {
    // s = aab; p = c*a*b
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }

        int m = s.length();
        int n = p.length();

        boolean dp[][] = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        for (int j = 1; j < n + 1; ++j) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) >= 'a' && p.charAt(j - 1) <= 'z') { // char does not match and p is not . or *
                    dp[i][j] = false;
                } else { // p.charAt(j - 1) = *
                        /**
                        if * 
                            prev char does not match  : only 0 case
                            prev char match or prev is '.' : 0 and 1 case
                        if same char [i-1][j-1]

                        diff char - false
                        */
                    // 0 case is sure
                    dp[i][j] = dp[i][j - 2];

                    if ((p.charAt(j - 2) == s.charAt(i - 1)) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}