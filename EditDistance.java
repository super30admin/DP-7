class Solution {
    //Space Complexity: (m*n)
    //Time Complexity: (m*n)
    public int minDistance(String word1, String word2) {
       if (word1.equals(word2)) 
       return 0;

       int m = word1.length();
       int n = word2.length();

       int[][] dp = new int[m+1][n+1];

       //filling the initial part up

       for (int j = 0;j <= m;j++){
        dp [j][0] = j;
       }
        for (int j = 0;j <= n;j++){
        dp [0][j] = j;
       }

       //filling up the rest of the dp array

       for ( int i=1 ; i <= m;i++){
        for (int j = 1; j <= n; j ++){
           //get add/ update and delete and put the min val in dp[i][j]
        if (word1.charAt(i-1) == word2.charAt(j-1)){
            dp[i][j] = dp[i-1][j-1];
        } else {
           int add =  1 +dp [i-1][j]; 
           int update =  1 +dp [i-1][j-1]; 
           int del =  1 +dp [i][j-1]; 
           dp[i][j] = Math.min (add, Math.min(update,del));
            }
        }
       }
       return dp[m][n];
    }
}
