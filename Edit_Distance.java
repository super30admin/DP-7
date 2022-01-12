// Time Complexity : O(M*N)
// Space Complexity : O(M*N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int minDistance(String word1, String word2) {
        int m=word1.length();
        int n=word2.length();
        int[][] dp=new int[m+1][n+1];
        
        for(int i=1;i<m+1;i++){
            dp[i][0]=i;
        }
        
         for(int i=1;i<n+1;i++){
            dp[0][i]=i;
        }
        
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                char c1=word1.charAt(i-1);
                char c2=word2.charAt(j-1);
                int delete=1+dp[i][j-1];
                int add=1+dp[i-1][j];
                int replace=1+dp[i-1][j-1];
                if(c1==c2){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                 dp[i][j]=Math.min(delete,Math.min(add,replace));
                }
                
            }
        }
        
        return dp[m][n];
    }
}