// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int j = 1; j <= n; j++) {
            char c = p.charAt(j - 1);
            if (c == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);
                if (pChar == '*') {
                    // one case if available
                    if (sChar == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        // one and zero case if available
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    if (sChar == pChar || pChar == '.')
                        dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        return dp[m][n];
    }

    public static void main(String[] args) {
        RegularExpressionMatching matcher = new RegularExpressionMatching();

        String s1 = "aa";
        String p1 = "a";
        System.out.println(matcher.isMatch(s1, p1)); // Output: false

        String s2 = "aa";
        String p2 = "a*";
        System.out.println(matcher.isMatch(s2, p2)); // Output: true

        String s3 = "ab";
        String p3 = ".*";
        System.out.println(matcher.isMatch(s3, p3)); // Output: true

        String s4 = "aab";
        String p4 = "c*a*b";
        System.out.println(matcher.isMatch(s4, p4)); // Output: true

        String s5 = "mississippi";
        String p5 = "mis*is*p*.";
        System.out.println(matcher.isMatch(s5, p5)); // Output: false
    }
}
