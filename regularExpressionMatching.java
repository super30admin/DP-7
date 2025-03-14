// Time Complexity : O(mn)
// Space Complexity : O(mn)

class Solution {
  public boolean isMatch(String s, String p) {
    if(s.equals(p)) return true;
    int m = s.length();
    int n = p.length();
    boolean[][] dp = new boolean[m+1][n+1];
    dp[0][0] = true;

    for(int i = 1; i < n+1; i++) {
      if(p.charAt(i-1) == '*') {
        dp[0][i] = dp[0][i-2];
      }
    }

    for(int i = 1; i<m+1; i++) {
      for(int j = 1; j<n+1; j++) {
        if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
          dp[i][j] = dp[i-1][j-1];
        }
        if(p.charAt(j-1) == '*') {
          dp[i][j] = dp[i][j-2];
          if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') {
            dp[i][j] = dp[i][j] || dp[i-1][j];
          }
        }
      }
    }
    return dp[m][n];
  }
}