// S30 Big N Problem #148 {Hard}
// 72. Edit Distance
// Time Complexity : O(m*n) where m,n are the lengths of corresponding strings 
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :None


// Your code here along with comments explaining your approach
class Solution {
    public int minDistance(String word1, String word2) {
        
        int m=word1.length();
        int n=word2.length();
        int [][]dp=new int[m+1][n+1];
        
        //first row
        for(int j=1;j<=n;j++){
            dp[0][j]=j;
        }
        
        //first column
        for(int i=1;i<=m;i++){
            dp[i][0]=i;
        }
        
        //remaining fill
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1])+1;
                }
            }
        }
        //System.out.println(dp[0][0]);
        return dp[m][n];
    }
}