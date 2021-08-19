//Time complexity - O(m*n)
//Space Complexity - O(m*n)

class Solution {
    public int minDistance(String word1, String word2) {
      int n = word1.length();
      int m = word2.length();
      
      int [][] dp = new int[m+1][n+1];
      
      //fill first row and column
      for(int j = 1; j<=n ;j ++) {
        dp[0][j] = j;
      }
      for(int i = 1; i<=m ;i ++) {
        dp[i][0] = i;
      }
      //fill remaining rows and columns
      for(int i = 1; i<=m ; i++) {
        for(int j =1 ;j<=n ; j++) {
          if(word1.charAt(j-1) == word2.charAt(i-1)){
            dp[i][j] = dp[i-1][j-1];
              // if characters are equal then no change .. get the value from diagonal up
          }
          else {      // 1+ min(top, diagonal up, left of the current)
            dp[i][j] = 1+ Math.min(dp[i-1][j], Math.min(dp[i-1][j-1],dp[i][j-1]));
          }
        }
      }
      return dp[m][n];
    }
}