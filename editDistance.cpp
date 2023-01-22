// Time Complexity : O(m*n) 
// Space Complexity : O(m*n) 
// Did this code successfully run on Leetcode : Yes 

class Solution {
public:
    int minDistance(string word1, string word2) {
        if(word1 == word2)
            return 0;

        int m = word1.length();
        int n = word2.length();
        // if(m < n)
        //     return minDistance(word2, word1);

        vector<vector<int>> dp(n+1, vector<int>(m+1));

        // update the first row where we create the other string by deletion
        //    "" h 0 r s e
        // ""  0 1 2 3 4 5
        for(int i=0; i<m+1; i++)
            dp[0][i] = i;
        
        // update the first column 
        //     "" 
        // ""  0
        // r   1
        // o   2
        // s   3        
        for(int j=0; j<n+1; j++)
            dp[j][0] = j;

        // Now there are 2 possibilities 
        // 1. The letters match, then we just get the solution from the diagonal 
        // i.e., hor -> r becomes ho -> "" which is at the diagonal
        // hors -> ros become hor -> ro

        // 2. When the letters don't match, we've got 3 options 
        // 1. Add : Top 
        // 2. Delete: Left
        // 3. Update: Diagonal
        // get the minimum of the three and add 1 for the operation we have done
        for(int i = 1; i<n+1; i++)
            for (int j=1; j<m+1; j++){
                if(word2[i-1] == word1[j-1])
                    dp[i][j] = dp[i-1][j-1];
                else {
                    dp[i][j] = 1 + min(min(dp[i-1][j],  dp[i][j-1]), dp[i-1][j-1]);
                }
            }

        return dp[n][m];
    }
};