// TC: O(m * n) all the characters in s and p are visited
// SC: O(m * n) dp array has the size of s and p respectively

// Runs successfully on leetcode
// if p and s string characters get match and then diagonal value is considered
// else if the * is detected then the value between the one row above value and 2 steps back is ORed.
public class RegularExpressionMatching {

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a")); // false
        System.out.println(isMatch("aa", "a*")); // true
        System.out.println(isMatch("ab", ".*")); // true
    }

    private static boolean isMatch(String s, String p) {
        if (s.equals(p))
            return true;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j < n + 1; j++) {
            if (p.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 2];
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}