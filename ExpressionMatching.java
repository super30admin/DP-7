// Time Complexity : O(N)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach
//Using bottom up - tabulation - optimising space
// Using tabulation
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        // fill top row
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[j] = dp[j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            boolean diagUp = dp[0];
            dp[0] = false; // have to make the first one false when reducing space
            for (int j = 1; j <= n; j++) {
                boolean temp = dp[j];
                if (p.charAt(j - 1) == '*') {
                    // for *
                    // if one case is available
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        // 1 case - just abve or 0 case - two steps back
                        dp[j] = dp[j] || dp[j - 2];
                    } else {
                        // only 0 case is available
                        // two steps back
                        dp[j] = dp[j - 2];
                    }

                } else {
                    // for rest of the characters
                    if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                        // get the diagonal up
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