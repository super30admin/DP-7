#include<iostream>
#include<bits/stdc++.h>
#include<vector>
using namespace std;
/*
We use a 1D array to check if parts of the string and pattern match as we go 
Matches any character, and * matches zero or more of the previous letter
We update the array row by row, using saved valuesfrom the previous step
*/
class Solution{
public:
    bool isMatch(string s, string p){
        int m = s.length(), n = p.length();
        vector<bool> dp(n+1, false);
        dp[0] = true;

        for(int j = 1; j <= n; j++){
            if(p[j-1] == '*') dp[j] = dp[j-2];
        }

        for(int i = 1; i <= m; i++){
            bool diagUp = dp[0];
            dp[0] = false;
            for(int j = 1; j <= n; j++){
                bool temp = dp[j];
                if(p[j-1] == '*'){
                    if(p[j-2] == s[i-1] || p[j-2] == '.'){
                        dp[j] = dp[j] || dp[j-2];
                    }else{
                        dp[j] = dp[j-2];
                    }
                }else{
                    if(p[j-1] == s[i-1] || p[j-1] == '.'){
                        dp[j] = diagUp;
                    }else{
                        dp[j] = false;
                    }
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
};
