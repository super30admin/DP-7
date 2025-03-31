// TC:O(m*n)
// SC:O(m*n)+O(max(m,n))

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        return recursion(s, p, 0, 0,dp);
    }

    private boolean recursion(String s, String p, int i, int j,boolean[][] dp) {
        if (j == p.length()) return i == s.length();

        if(dp[i][j]!=false) return dp[i][j];
        boolean firstMatch = (i < s.length() && 
                              (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            return dp[i][j]=recursion(s, p, i, j + 2,dp) || (firstMatch && recursion(s, p, i + 1, j,dp));
        }

        if (firstMatch) {
            return dp[i][j]=recursion(s, p, i + 1, j + 1,dp);
        }

        return dp[i][j]=false;
    }
}
