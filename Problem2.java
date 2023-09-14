class Solution {
    public boolean isMatch(String s, String p) {
        Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];
        return helper(s, p, dp, 0, 0);
    }

    public boolean helper(String s, String p, Boolean[][] dp, int i, int j) {
        if (i >= s.length() && j >= p.length())
            return true;
        if (j >= p.length())
            return false;
        if (dp[i][j] != null)
            return dp[i][j];

        boolean first = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            return dp[i][j] = helper(s, p, dp, i, j + 2) || (first && helper(s, p, dp, i + 1, j));
        }
        if (first) {
            return dp[i][j] = helper(s, p, dp, i + 1, j + 1);
        }
        return false;
    }
}