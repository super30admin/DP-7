// Time Complexity : O(n*m)
// Space Complexity : O(n*m) for dp array 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


class Solution {
    public int minDistance(String word1, String word2) {
         int m=word1.length()+1;
      int n=word2.length()+1;
        
      int[][]dp=new int[n][m];
        
        for(int i=0;i<m;i++){
            dp[0][i]=i;
        }
        for(int j=0;j<n;j++){
            dp[j][0]=j;
        }
    for(int i=1;i<n;i++){
        for(int j=1;j<m;j++){
            if(word1.charAt(j-1)==word2.charAt(i-1)){
                dp[i][j]=dp[i-1][j-1];
            }
            else{
                dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
            }
        }
    }
        return dp[n-1][m-1];
        
    }
}