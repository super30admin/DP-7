//72. Edit Distance - https://leetcode.com/problems/edit-distance/description/

//DP
//Time Complexity: O(m*n)
//Space Complexity: O(m*n) ~ matrix of size 'm' and 'n'
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m+1][n+1]; //extra row and col for '-' strings

        for(int j=1; j<=n; j++){
            dp[0][j] = j; //fill 1st row
        }
        for(int i=1; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(j == 0){ //first col
                    dp[i][0] = i; //fill first col
                } else {
                    if(word1.charAt(i-1) == word2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1]; //diagonal-up
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]));
                    }
                }
            }
        }
        return dp[m][n];
    }
}

//Optimized Space
//Time Complexity: O(m*n)
//Space Complexity: O(m*n) ~ matrix of size 'm' and 'n'
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[] dp = new int[n+1]; //extra row and col for '-' strings

        for(int j=1; j<=n; j++){
            dp[j] = j;
        }
        int diagUp = 0;
        for(int i=1; i<=m; i++){
            for(int j=0; j<=n; j++){
                int temp = dp[j];
                if(j == 0){ //first col
                    dp[j] = i; //fill first col
                } else {
                    if(word1.charAt(i-1) == word2.charAt(j-1)){
                        dp[j] = diagUp; //diagonal-up
                    } else {
                        dp[j] = 1 + Math.min(dp[j-1], Math.min(dp[j], diagUp));
                    }
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
}
