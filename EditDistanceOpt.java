//Time complexity - O(m*n)
//Space Complexity - O(m*n)

class Solution {
    public int minDistance(String word1, String word2) {
      int n = word1.length();
      int m = word2.length();
      if(m > n) return minDistance(word2,word1);
      if(n == 0) return word2.length();
      int [] dp = new int[n+1];
      
      //fill first row and column
      for(int j = 1; j<=n ;j ++) {
        dp[j] = j;
      }
      int temp1;
      int temp2;
      
      //fill remaining rows and columns
      for(int i = 1; i<=m ; i++) {
        temp1 = dp[0];
        dp[0] = i;
        for(int j =1 ;j<=n ; j++) {
          temp2 = dp[j];
          if(word1.charAt(j-1) == word2.charAt(i-1)){
            dp[j] = temp1;
              // if characters are equal then no change .. get the value from diagonal up
          }
          else {      // 1+ min(top, diagonal up, left of the current)
            dp[j] = 1+ Math.min(dp[j], Math.min(dp[j-1],temp1));
          }
          temp1 = temp2;
        }
      }
      return dp[n];
    }
}