// Time Complexity : O(m * n) where m and n the lengths of string and pattern respectively
// Space Complexity : O(m * n) where m and n the lengths of string and pattern respectively
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class regexMatching {
    public boolean isMatch(String s, String p) {
        if (s == null) return p == null;
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int i = 1; i <= pLen; i++) {
            char c = p.charAt(i-1);
            if (c == '*') dp[0][i] = dp[0][i-2];
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                char c = s.charAt(i-1);
                char d = p.charAt(j-1);
                if (c == d || d == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } 
                else if (d == '*') {
                    dp[i][j] = dp[i][j-2];
                    if (p.charAt(j-2) == c || p.charAt(j-2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[sLen][pLen];
    }
}