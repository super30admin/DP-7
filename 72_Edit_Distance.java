    /*  Explanation
    # Leetcode problem link : https://leetcode.com/problems/edit-distance/
    Time Complexity for operators : o(n*m) .. 
    Extra Space Complexity for operators : o(n*m) .. size of matrix
    Did this code successfully run on Leetcode : NA
    Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
        # Basic approach : 
        # Optimized approach: 
                              
            # 1. 
                    A) Create DP Matrix of n rows and m columns.
                    B) First populate the row and assign value of j to it.
                    C) Populate column, assign value of i to it.
                    D) Now DP, 2 for loops starting from 1.
                       if word1 charscters is equal to the word2 character then get the value from its diagonal.
                    E) If not, thentake the min of diagonal, left and value from upper row.
                    F) At the end, return last dp[n-1][m-1].
    */  

class Solution {
    public int minDistance(String word1, String word2) {
        
        int n = word2.length() + 1;
        int m = word1.length() + 1;
        
        int dp[][] = new int[n][m];
        
        //populate row
        for(int j=0;j<m;j++){
            dp[0][j] = j;
        }
        
        //populate column
        for(int i=0;i<n;i++){
            dp[i][0] = i;
        }
        
        // DP
        
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(word1.charAt(j-1) == word2.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1])) + 1;
                }
            }
        }
        
        return dp[n-1][m-1];
    }
}