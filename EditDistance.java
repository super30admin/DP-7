/*
 * Time Complexity : O(m*n)
 * Space Complexity : O(m*n)
 * Did this code successfully run on Leetcode : yes
 * Any problem you faced while coding this : no
 */
class Solution {
    public int minDistance(String word1, String word2) {
        
        int n = word1.length();
        int m = word2.length();

        int dp[][] = new int[m+1][n+1];

        //add -> up  replace -> diagonal up  delete -> left
        //if matching diagonal up


        for(int i =0;i<=n;i++){
            dp[0][i] = i;
        }

        for(int i =1;i<=m;i++){
            dp[i][0] = i;
        }


        for(int i =1;i<=m;i++){
            for(int j= 1;j<=n;j++){
                if(word2.charAt(i-1) == word1.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]));
                }
            }
        }

        return dp[m][n];

        
    }
}