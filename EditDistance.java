class Solution {
    public int minDistance(String word1, String word2) {
      int m = word1.length();
      int n = word2.length();
      int[] dp = new int[n+1]; 
      //filling top row
      for(int j = 0 ; j <=n ; j++ ){
        dp[j] = j;
      }
      //start filling rest of the matrix
      for(int i = 1; i <=m ; i++){
        int diagup = dp[0];
        dp[0] = i;
        for(int j = 1 ; j<=n ; j++){
          int temp = dp[j];
          //if characters are same at pointers , no change then diag up
          if(word1.charAt(i-1) == word2.charAt(j-1)){
            dp[j] = diagup;
          }
          else{
            int insert = 1 + dp[j] ; //top
            int delete = 1 + dp[j-1] ; //left
            int update = 1 + diagup ; //diag up
            dp[j] = Math.min(insert , Math.min(delete , update));
          }
          diagup = temp;

        }
      }
      return dp[n];
        
    }
}