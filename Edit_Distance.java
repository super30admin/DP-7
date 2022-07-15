//Time Complexity : O(m*n)
//Space Complexity : O(m*n)
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if(word1.equals(word2)) return 0;

        int [][] dp = new int[m+1][n+1];
        //top row
        for(int j = 1; j <= n; j++){
            dp[0][j] = j;
            
        }
        //first column
        for(int i = 1; i <= m; i++){
            dp[i][0] = i;
        }
         for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                //if word1 char is equal to word2 char then no change is needed 
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                //minimum of update,delete and add and add one to it
                else{
                    dp[i][j]= 1+ Math.min(dp[i][j-1],Math.min(dp[i-1][j],dp[i-1][j-1]));
                }
            }
        }
        
        return dp[m][n];
    }
}