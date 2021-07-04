/*

https://leetcode.com/problems/edit-distance

Did it run on leetcode: Yes
Time Complexity: 0(m*n)
Space Complexity: 0(m*n)

Algorithm:
- `i` character at index i-1 in word1 and `j` character it index j-1 in word2
- Every dp[i][j] denotes the minimum distance required to convert word1[0..i-1] --> word2[0...j-1]
- Two decisions havd to be made at every point in the matrix
- if char at word1[i-1]==word2[j-1] then dp[i][j] = dp[i-1][j-1]
- if char are not equal then dp[i][j] = min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j])+1, as we can come to the current state from
three positions.

*/


class Solution {
    public int minDistance(String word1, String word2) {
        
        if(word1.length()>word2.length()){
            return this.minDistance(word2,word1);
        }
        
        int m = word1.length()+1;
        int n = word2.length()+1;
        
        
        int[][] dp = new int[m][n];
        
        for(int i=0;i<m;++i){
            dp[i][0] = i;
        }
        
        for(int j=0;j<n;++j){
            dp[0][j]=j;
        }
        
        for(int i=1;i<m;++i){
            char c1 = word1.charAt(i-1);
            for(int j=1;j<n;++j){
                char c2 = word2.charAt(j-1);
                if(c1==c2){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                }
            }
        }
        
        return dp[m-1][n-1];
        
    }
}