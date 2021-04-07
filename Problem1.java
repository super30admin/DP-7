//time complexity-O(M*N)
//Space complexity-O(M*N)
//Ran on leetcode-Yes
//Solution with comments-
class Solution {
    public int minDistance(String word1, String word2) {//each index in dp matrix is updated according to min of three operations
        int n = word1.length();
        int m = word2.length();
        int[][] dp= new int[n+1][m+1];
        dp[0][0]=0;
        for(int i=1;i<n+1;i++){
            dp[i][0]=1+dp[i-1][0];
        }
         for(int j=1;j<m+1;j++){
            dp[0][j]=1+dp[0][j-1];
        }
        
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }
                else{
                    dp[i][j]=1+Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]));
                }
            }
        }
        return dp[n][m];
    }
}
//here _ is null and row to column relation is as such  dp[1][1] means transformations neede to _r -> _h
//         _ h o r s e
//      _  0 1 2 3 4 5
//      r  1 1 2 2 3 4
//      o  2 2 1 2 3 4
//      s  3 3 2 2 2 3
