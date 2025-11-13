#include<iostream>
#include<bits/stdc++.h>
#include<vector>
using namespace std;
/*
We use just one row of DP and update it iteratively for each character in word1
A diagUp variable tracks the top left diagponal value as we go 
If characters match, we copy from the diagonal; otherwise, take min of insert, delete, replace 
*/
class Solution{
public:
    int minDistance(string word1, string word2){
        int m = word1.length();
        int n = word2.length();
        vector<int> dp(n + 1);

        for(int j = 1; j <= n; j++){
            dp[j] = j;
        }

        for(int i = 1; i <= m; i++){
            int diagUp = dp[0];
            for(int j = 0; j <= n; j++){
                int temp = dp[j];
                if(j == 0) dp[j] = i;
                else if(word1[i-1] == word2[j-1]){
                    dp[j] = diagUp;
                }else{
                    dp[j] = 1 + min({dp[j], diagUp, dp[j-1]});
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
};
