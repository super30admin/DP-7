/*
Algorithm (Bottom-up DP using 1D rolling array for pattern):
- Use a 1D boolean array dp of length n+1 (n = p.length()) where dp[j] indicates whether
  the current prefix of s (up to i) matches the prefix of p (up to j).
- Initialize dp for the empty s (i=0) by handling patterns with '*'.
- For each character in s (i from 1..m) update dp in place using a diagUp temporary to represent dp[i-1][j-1].
- Handle '*' by either zero occurrences (dp[j-2]) or one/more if preceding char matches (dp[j]).

Time Complexity: O(m * n) where m = s.length(), n = p.length().
Space Complexity: O(n) for the 1D dp array.
LeetCode: https://leetcode.com/problems/regular-expression-matching/
*/

public class RegularExpressionMatchingDp2 {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[i] = dp[i - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            boolean diagUp = dp[0];
            dp[0] = false;
            for (int j = 1; j <= n; j++) {
                //* char
               boolean temp = dp[j];
                if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[j] = dp[j - 2] || dp[j];
                    } else {
                        dp[j] = dp[j - 2];
                    }

                } else {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[j] = diagUp;
                    } else {
                        dp[j] = false;
                    }
                }
                diagUp = temp;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatchingDp2().isMatch("aa", "a"));
    }
}
