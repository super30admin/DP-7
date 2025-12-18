/*
Algorithm (Bottom-up DP using 2D table):
- dp[i][j] means whether first i chars of s match first j chars of p.
- Handle '*' by either treating it as zero occurrence (dp[i][j-2]) or if preceding char matches current s char, one-or-more occurrence (dp[i-1][j]).
- For normal char or '.', match if characters equal and dp[i-1][j-1] is true.

Time Complexity: O(m * n) where m = s.length(), n = p.length().
Space Complexity: O(m * n) for the DP table.
LeetCode: https://leetcode.com/problems/regular-expression-matching/
*/

public class RegularExpressionMatchingDp1 {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //* char
                if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }

                } else {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatchingDp1().isMatch("aab", "c*a*b"));
    }
}
