// Time Complexity :O(nm) - n & m are the size of the words
// Space Complexity : O(nm) - DP Matrix
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
public:
    int minDistance(string word1, string word2) {
        int m = word2.length();
        int n = word1.length();
        int dp[m+1][n+1];
        for(int i = 0; i <= n;i++){
            dp[0][i] = i;
        }
        for(int i = 1; i<=m;i++){
            dp[i][0] = i;
        }
        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=n;j++){
                if(word2[i-1] == word1[j-1]) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = 1 + min(dp[i-1][j],min(dp[i-1][j-1],dp[i][j-1]));
            }
        }
        return dp[m][n];
    }
};