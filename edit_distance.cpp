// Time complexity-O(n*m)-
// space complexity O(n*m);
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this : no

// build a dp array to represent the repeating sub problems
// if the char in the two words are equal just use the precedding diagonal value
// esle pick the mininmum between the update delete and add operations then add 1 to it




class Solution {
public:
    int minDistance(string word1, string word2) {
        
        int m = word1.size();
        int n =word2.size();
        
        if(n==0){  return m; }
        vector<vector<int>>dp(m+1,vector<int>(n+1));
        // top row
        for(int i=1;i<=m;i++){
            dp[i][0]=i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        
        
        for(int i =1; i <=m; i++){
            for(int j =1; j<=n;j++){
              if(word1[i-1]== word2[j-1]){
                  dp[i][j] = dp[i-1][j-1];
              }
            else{
                    dp[i][j] = 1+  min( dp[i-1][j-1], min(dp[i-1][j], dp[i][j-1]) );
                }
        }
        
        
    }
      return dp[m][n];  
    }
};