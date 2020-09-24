//Time Complexity : O(m*n)
//Space Complexity : O(m*n)
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int [][] dp = new int[m+1][n+1];
         // fill the 1st row and column with the minimum operations needed for them 
        for(int i = 0; i < dp[0].length; i++)
        {
            dp[0][i] = i;
        }
        
         for(int i = 0; i< dp.length; i++){
            dp[i][0] = i;
        }
        
        // start filling the dp array
        for(int i = 1; i < dp.length; i++)
        {
            for(int j = 1 ; j < dp[0].length; j++)
            {
                if(word1.charAt(i - 1) == word2.charAt(j-1)) // if character matches then simply put the diagonal element
                {
                    dp[i][j] = dp[i-1][j-1];
                }
                else
                {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                }
            }
        }
        return dp[m][n];
    }
}