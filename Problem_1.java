// Time Complexity : O(n*n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Three line explanation of solution in plain english
// if same then get it from hoeizontal top, if not then 1 + min of all
// Your code here along with comments explaining your approach
class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if(n > m) return minDistance(word2,word1);
        int [] dp = new int [n+1];
        //top
        for(int i = 0; i < dp.length; i++){
            dp[i] = i;
        }
        for( int i = 1; i <= m; i++){
            int temp = dp[0];
            for( int j = 0; j <= n; j++ ){
                int temp2 = dp[j];
                if( j == 0){
                    dp[j] = i;
                }else{
                    if(word1.charAt(j-1) == word2.charAt(i-1)){
                        dp[j] = temp;
                    }else{
                        dp[j] = 1 + Math.min( dp[j-1], Math.min(dp[j], temp));
                    }
                }
                temp = temp2;
            }
        }
        return dp[n];
    }
}

// using 2d array
// class Solution {
//     public int minDistance(String word1, String word2) {
//         int m = word1.length();
//         int n = word2.length();
//         int [][] dp = new int [m+1][n+1];
//         // top col
//         for(int i = 0; i < dp.length; i++){
//             dp[i][0] = i;
//         }
//         // top row
//         for(int j = 0; j < dp[0].length; j++){
//             dp[0][j] = j;
//         }
//         // all
//         for( int i = 1; i < dp.length; i++){
//             for( int j = 1; j < dp[0].length; j++){
//                 if(word1.charAt(i-1) == word2.charAt(j-1)){
//                     dp[i][j] = dp[i-1][j-1];
//                 }else{
//                     dp[i][j] = 1 + Math.min( dp[i][j-1],
//                                             Math.min( dp[i-1][j], dp[i-1][j-1]));
//                 }
//             }
//         }
//         return dp[m][n];
//     }
// }