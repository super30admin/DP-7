/* 
    Time Complexity                              :  O(M*N)
    Space Complexity                             :  O(M*N) - Conventional DP
                                                    O(N) - Space Optimized
    Did this code successfully run on Leetcode   :  Yes
    Any problem you faced while coding this      :  No
*/

#include <bits/stdc++.h> 
using namespace std;  


class Solution {
public:
    int minDistance(string word1, string word2) {
        // return conventionalDP(word1, word2);
        return spaceOptimized(word1, word2);
    }
    
    int conventionalDP(string word1, string word2) {
        int n = word1.size(), m = word2.size();
        vector<vector<int>> dp(n+1,vector<int>(m+1,0));
        // add operation
        for(int j=1;j<=m;j++) {
            dp[0][j] = j;
        }
        
        // delete operation to make '_' equal to '_<characters from word1>'
        for(int i=1;i<=n;i++) {
            dp[i][0] = i;
        }
        
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if(word1[i-1] == word2[j-1]) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + min(min(dp[i-1][j],dp[i][j-1]), dp[i-1][j-1]);
                }
            }
        }
        
        return dp[n][m];
    }
    
    
    int spaceOptimized(string word1, string word2) {
        int n = word1.size(), m = word2.size();
        if(m > n) {
            spaceOptimized(word2, word1);
        }
        
        vector<int> dp(m+1,0);
        for(int i=0;i<=m;i++) {
            dp[i] = i;
        }
        
        for(int i=1;i<=n;i++) {
            int temp = i-1;
            dp[0] = i;
            for(int j=1;j<=m;j++) {
                int prev = dp[j];
                if(word1[i-1] == word2[j-1]) {
                    dp[j] = temp;
                } else {
                    dp[j] = 1 + min(min(temp, prev), dp[j-1]);
                }
                temp = prev;
            }
        }
        
        return dp[m];
        
    }
};