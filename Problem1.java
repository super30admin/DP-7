// 72 Edit distance
// TC -> m * n
// space -> m * n

class Solution {
    public int minDistance(String word1, String word2) {
        
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        // initialize first row
        for(int i = 0; i < n + 1; i++){
            
            dp[0][i] = i;
        }
        
        // initialize first col
        
        for(int i = 0; i < m + 1; i++){
            
            dp[i][0] = i;
        }
        
        
        for(int r = 1; r < m + 1; r++){
            for(int c = 1; c < n + 1; c++){
                
                char letter1 = word1.charAt(r - 1);
                char letter2 = word2.charAt(c - 1);
                
                if(letter1 == letter2){
                    dp[r][c] = dp[r - 1][c - 1];
                }else{
                    dp[r][c] = Math.min(dp[r - 1][c - 1], Math.min(dp[r - 1][c], dp[r][c - 1])) + 1;
                }
                
                
            }
        }
        
        return dp[m][n];
    }
}