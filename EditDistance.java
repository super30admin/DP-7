class EditDistance {
    
    /*
      Time : O(M * N)  | dp array iteration
      Space : O(M * N) | dp array
      Leetcode :YES
    */
  
    /*
      Approach :
      1. For each character we have three choices, Update, Delete, Insert.
      2. Draw tree and observer repeated sub problem.
      3. DP matrix of rows = word2.length, col = word1.length
      4. Iterate over matrix. Observe, if character is same at current position then we simply take diagonal element.
      5. If char is different then take minimum of Top, left and diagonal.
      6. return last item in the array.
    */
    public int minDistance(String word1, String word2) {
        if(word1 == null || word2 == null) return 0;
        
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[n+1][m+1];
        
        for(int i = 0; i <= n ; i++){
            dp[i][0] = i;            
        }
        
        for(int i = 0; i <= m ; i++){
            dp[0][i] = i;
        }
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(word1.charAt(j-1) == word2.charAt(i - 1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])) + 1;
                }
            }
        }
        
        return dp[n][m];
    }
}
