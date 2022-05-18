//TC : O(M*N)
//SC : O(M*N)

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int [][] dp = new int[m+1][n+1];
        
        for(int j = 0; j< dp[0].length; j++){
            dp[0][j] = j; // First row 
        }
        
        for(int i = 0; i< dp.length; i++){
            dp[i][0] = i; // First Column
        }
        
        for(int i = 1; i<=m ; i++){
            for(int j = 1; j <= n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{ 
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]) ) + 1;
                                //Min of  UPDATE CASE(Diagonal),  DELETE CASE(1 Step back (Left)), INSERT CASE(1 step Up) + 1
                
                }
            }
        }
        
        return dp[m][n];
    }
}

