
//TC(M*N)- SC(M*N)
public class RegularExpressionMatchStarDot {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // Empty string matches empty pattern

        // Initialize for patterns like a*, a*b*, etc.
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                if (j >= 2) {
                    dp[0][j] = dp[0][j - 2];
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);

                if (pChar == '*') {
                    // Match zero of the preceding element
                    dp[i][j] = dp[i][j - 2];

                    // Match one or more of the preceding element
                    if (p.charAt(j - 2) == sChar || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    // Direct match or dot match
                    if (pChar == sChar || pChar == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }

        return dp[m][n];
    }


    public static void main(String[] args) {
        RegularExpressionMatchStarDot sol=new RegularExpressionMatchStarDot();
        boolean result=sol.isMatch("aab","c*a*b");
        System.out.println(result);
    }
}
