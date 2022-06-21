/* 
    Time Complexity                              :  O(M*N) where M is the size of the string and N is the size of the pattern.
    Space Complexity                             :  O(M*N) size of the dp array.
    Did this code successfully run on Leetcode   :  Yes
    Any problem you faced while coding this      :  No
*/

#include <bits/stdc++.h> 
using namespace std;  

class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.size();
        int n = p.size();
        vector<vector<bool>> dp(m+1,vector<bool>(n+1, false));
        dp[0][0] = true;
        for(int j=1;j<=n;j++) {
            if(p[j-1] == '*') {
                dp[0][j] = dp[0][j-2];
            }
        }
        
        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                if(s[i-1] == p[j-1] || p[j-1] == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if(p[j-1] == '*' ){
                    // 0 case
                    dp[i][j] = dp[i][j-2];
                    // 1 case
                    if(s[i-1] == p[j-2] || p[j-2] == '.') {
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                } 
            }
        }
        
        
        return dp[m][n];
    }
};