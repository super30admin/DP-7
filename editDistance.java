// Time Complexity : O(m x n)
// Space Complexity : O(m)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// DP space optimized!
class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length(); int m = word2.length();
        if(word1.length() > word2.length()) return minDistance(word2, word1);
        
        int [] dp = new int[n+1];
        
        // top row
        for(int j=0; j<dp.length; j++){
            dp[j] = j;
        }
       
        for(int i=1; i<=m; i++){
            int temp = dp[0];
            for(int j=0; j<=n; j++){
                int temp2 = dp[j];
                if(j == 0){
                    dp[j] = i;
                } else{
                    if(word1.charAt(j-1) == word2.charAt(i-1)){
                        dp[j] = temp; // diag up
                    } else{
                        dp[j] = 1 + Math.min(dp[j-1], Math.min(dp[j], temp));
                    }
                }
                temp = temp2;
            }
        }
        return dp[n];
    }
}

// ****************************************
// Time Complexity : O(m x n)
// Space Complexity : O(m x n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// DP basic implementation
class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length(); int m = word2.length();
        int [][] dp = new int[m+1][n+1];
        
        // top row
        for(int j=0; j<dp[0].length; j++){
            dp[0][j] = j;
        }
        
        // first column
        for(int i=1; i<dp.length; i++){
            dp[i][0] = i;
        }
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if( word1.charAt(j-1) == word2.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else{
                    dp[i][j] = 1 + Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]));
                }
            }
        }
        return dp[m][n];
    }
}