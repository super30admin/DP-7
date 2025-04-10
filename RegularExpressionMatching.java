/*
 * TC: O(m*n)
 * SC: O(m*n)
 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int n = p.length();
        int m = s.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // empty strings match
        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                if(i == 0) {
                    if (j == 0) continue;
                    if(p.charAt(j - 1) == '*') {
                        dp[i][j] = dp[i][j - 2]; // 2 steps back
                    } else {
                        dp[i][j] = false; //nothing matches an empty String
                    }
                    continue;
                }
                if(j == 0) {
                    dp[i][j] = false; //nothing matches an empty String
                    continue;
                }
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    // if characters match (or is a .) look at the diagonal
                    // as this is a match in any case
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                if(p.charAt(j - 1) == '*') {
                    // for a matching preceding char, take the OR of upper and 2 steps back
                    if(p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] | dp[i][j - 2];
                    }
                    // else just take it from 2 steps back
                    else {
                        dp[i][j] = dp[i][j - 2];
                    }
                    continue;
                }
                // just mismatched chars
                dp[i][j] = false;
            }
        }
        return dp[m][n];
    }
}
