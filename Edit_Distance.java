class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        if(n == 0) return word2.length();
        
        int [][] dp = new int[m+1][n+1];
        //top row
        for(int j = 0; j < dp[0].length; j++){
            dp[0][j] = j;
        }
        
        //1st column
        for(int i = 1; i < dp.length; i++){
            dp[i][0] = i;
        }
        
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(word1.charAt(j-1) == word2.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                }
            }
        }
        return dp[m][n];
        
        
    }
}

//TC: O(m*n)
//SC: O(m*n)
