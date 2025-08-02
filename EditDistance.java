// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Approach : Use DP. For each character in word1, check if it matches with character in word2, if yes check if previous part of word1,
/// matches with previous part of word2. Else, check if adding, deleting or updating character we can match word1 with word2

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int dp[][] = new int[m+1][n+1];
        for(int i=1;i<=n;i++){
            dp[0][i] = i;
        }
        for(int j=1;j<=m;j++){
            dp[j][0] = j;
        }

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                char c1 = word1.charAt(i-1);
                char c2 = word2.charAt(j-1);
                if(c1 == c2){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]))+1;
                }
            }
        }
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return dp[m][n];
    }
}