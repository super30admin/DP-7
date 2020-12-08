class Solution {//Time and space of O(NM)
    public int minDistance(String word1, String word2) {
        
        int row = word1.length();
        int col = word2.length();
        
        int[][] dp = new int[row+1][col+1];
        
        //first row and first col
        for(int i = 0 ; i< row +1 ;i++){
            dp[i][0] = i;
        }
        for(int j = 0 ; j<col+1 ; j++){
            dp[0][j] = j;
        }
        
        for(int i = 1 ; i< row +1 ;i++){
            for(int j = 1 ; j<col+1 ; j++){
                int insert_op = 1 + dp[i][j-1];
                int delete_op = 1 + dp[i-1][j];
                int replace_op = 1 + dp[i-1][j-1];
                
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(insert_op , Math.min(delete_op,replace_op));
                }
            }
        }
        return dp[row][col];
    }
}