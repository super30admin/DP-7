// Time Complexity : O(n*m)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p))
            return true;

        int m = s.length();
        int n = p.length();

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*')
                dp[j] = dp[j - 2];
        }

        for (int i = 1; i <= m; i++) {
            boolean diagUp = false;
            if (i == 1) {
                diagUp = true;
                dp[0] = false;
            }

            for (int j = 1; j <= n; j++) {
                boolean temp = dp[j];
                if (p.charAt(j - 1) != '*') {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[j] = diagUp;
                    } else {
                        dp[j] = false;
                    }
                } else {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[j] = dp[j - 2] || dp[j];
                    } else {
                        dp[j] = dp[j - 2];
                    }
                }
                diagUp = temp;
            }

        }

        return dp[n];
    }
}