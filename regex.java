// Time Complexity: O(m * n) – process each (i, j) state once with O(1) work via a rolling 1D DP.
// Space Complexity: O(n) – one DP array of length n+1 (plus O(1) temps).

// Initialize dp for empty string: dp[0]=true; for pattern prefixes like "a*" or "ab", set dp[j]=dp[j-2] to allow zero occurrences.
// For i=1..m, sweep j=1..n keeping diagUp=previous dp[j-1]; if p[j-1] is a letter or '.', set dp[j]=diagUp when it matches s[i-1], else false.
// If p[j-1]=='', let prev=p[j-2]; set dp[j]=dp[j-2] (use '' as zero) OR dp[j] (extend '*' to cover s[i-1]) when s[i-1] matches prev; after the loop, answer is dp[n].

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int j = 1; j <= n; j++) {
            char pChar = p.charAt(j - 1);
            if (pChar == '*') {
                dp[j] = dp[j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            boolean diagUp = dp[0];
            dp[0] = false;
            for (int j = 1; j <= n; j++) {
                boolean temp = dp[j];
                char pChar = p.charAt(j - 1);
                if (pChar == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[j] = dp[j - 2] || dp[j];
                    } else {
                        dp[j] = dp[j - 2];
                    }
                } else {
                    if (pChar == s.charAt(i - 1) || pChar == '.') {
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
}
