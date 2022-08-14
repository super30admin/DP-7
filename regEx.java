// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Main {
    // approch 1 using dp matrix
    public static boolean isMatch(String s, String p) {
        // check if s and p matches then return true
        if (s.equals(p))
            return true;
        int m = s.length();
        int n = p.length();
        // dp matrix
        boolean[][] dp = new boolean[m + 1][n + 1];
        // initially empty string matches with empty string
        // so dp[0][0] would be true
        dp[0][0] = true;
        // handling first row in dp matrix
        for (int j = 1; j <= n; j++) {
            // if we found * at any index we check its second
            // previous index if that is true mark it true
            // else mark it false
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // main logic
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // check if character is not equal to * then
                if (p.charAt(j - 1) != '*') {
                    // it will check if character is matches with the input string
                    // char then we get from the previous diagonal
                    if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    // if char is *;
                    // if char before * is matching or it is . then update dp
                    // from the previous row or same row col - 2;
                    dp[i][j] = dp[i][j - 2]; // zero case
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatch(s, p));
    }
}