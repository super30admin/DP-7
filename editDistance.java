 class Solution {
    public int minDistance(String word1, String word2) {
        int col = word1.length();
        int row = word2.length();
        if(word2.length() == 0 ) return word1.length();
        int [][]dp = new int [row + 1][col + 1];
        
        //first row
        for(int j = 0; j < dp[0].length; j++){
            dp[0][j] = j;
        }
        
        //first col
        for(int i = 1; i < dp.length; i++){
            dp[i][0] = i;
        }
        
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                //if elements at index i and j are equal
                if(word2.charAt(i - 1) == word1.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    //take min of neighbouring 3 elements and add 1
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
