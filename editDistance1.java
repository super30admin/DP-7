//Time: O(MN)
//Space: O(MN)


//This approach uses a dp matrix
class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2))
            return 0;
        
        int m = word2.length();
        int n = word1.length();
        
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        //first row
        for(int i = 1; i <= m; i++)
            dp[i][0] = i;
        
        for(int j = 1; j <= n; j++)
            dp[0][j] = j;
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                
                //chars are not equal
                if(word1.charAt(j - 1) != word2.charAt(i - 1)){
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
                //equal
                else{
                    dp[i][j] = dp[i-1][j - 1];
                }
                
            }
        }
        
        return dp[m][n];
    }
}