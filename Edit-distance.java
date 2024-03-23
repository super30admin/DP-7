/*Time Complexity: O(M*N)

Space Complexity: O(M*N + M*N)

Did this code successfully run on Leetcode : Yes

Approach: Memoization - Exploring all possiblies and storing repeated subprobs in dp array.

Prob: 72. Edit Distance
*/

import java.util.Arrays;

class Solution {
    int helper(String word1, String word2, int i,int j, int [][] dp){
        if(i < 0) 
            return j+1;
        if(j < 0) 
            return i+1;
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if(word1.charAt(i) == word2.charAt(j)){
            return dp[i][j] = 0 + helper(word1,word2,i-1,j-1,dp);
        }
        else{
            return dp[i][j] = 1 + Math.min(helper(word1,word2,i-1,j,dp),
            Math.min(helper(word1,word2,i,j-1,dp),helper(word1,word2,i-1,j-1,dp)));
        }
    }
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int [][] dp = new int[n][m];
        for(int []rows : dp){
            Arrays.fill(rows,-1);
        }
        return helper(word1,word2,n-1,m-1,dp);
    }
}