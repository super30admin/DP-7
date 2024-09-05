// Time Complexity: O(m*n)
// Space Complexity: O(n)

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        if (m == 0 || n == 0)
            return false;

        boolean[] dp = new boolean[n + 1];

        dp[0] = true;
        for (int j = 1; j <= n; j++)
            if (p.charAt(j - 1) == '*')
                dp[j] = dp[j - 2];

        for (int i = 1; i <= m; i++) {
            boolean diagUp = dp[0];
            dp[0] = false;
            for (int j = 1; j <= n; j++) {
                boolean temp = dp[j];
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                    dp[j] = diagUp;
                else if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')
                        dp[j] = dp[j] || dp[j - 2];
                    else
                        dp[j] = dp[j - 2];
                } else
                    dp[j] = false;
                diagUp = temp;
            }
        }

        return dp[n];
    }
}