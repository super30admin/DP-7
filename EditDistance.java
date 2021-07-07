


class Solution {
    public int minDistance(String word1, String word2) {
        
        int n = word2.length()+1;
        int m = word1.length()+1;
        
        int[][] dp = new int[n][m];
        
        // fill the initial column
                             
        for(int i=1; i<n; i++){
            dp[i][0] = i;
        }
        
    // fill the initial row
                             
        for(int i=1; i<m; i++){
            dp[0][i] = i;
        }
        
        for(int i=1; i<n; i++){
            for(int j=1 ; j<m ; j++){
                // case1 when chars are equal
                if(word2.charAt(i-1) == word1.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                // case2 when char are not equal
                else{
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                }
            }
        }
        return dp[n-1][m-1];
    }
}
