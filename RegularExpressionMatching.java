//time complexity- O(n*m)
//space complexity- O(n*m)
public class RegularExpressionMatching {
    static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            char alpha = p.charAt(i - 1);
            if (alpha == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                char alpha = p.charAt(j - 1);
                if (alpha == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    if (alpha == s.charAt(i - 1) || alpha == '.') {
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
        String  s = "aa", p = "a*";
        System.out.println("S->" + s + ",  P->"+ p);
        System.out.println("Is the words matched? -> " + isMatch(s, p));

    }
}
