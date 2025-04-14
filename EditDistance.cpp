// Time Complexity: O(m*n)
// Space Complexity: O(m*n)
// Did you run the code on Leetcode? Yes

// Approach:
// 1. Create a 2D dp array of size (m+1)*(n+1) where m is the length of word1 and n is the length of word2.
// 2. dp[i][j] will store the minimum number of operations required to convert word1[0...i-1] to word2[0...j-1].
// 3. Initialize dp[0][0] = 0, as no operations are needed to convert empty string to empty string.
// 4. Fill the first row of dp array. dp[0][j] = j, as we need j insertions to convert empty string to word2[0...j-1].
// 5. Fill the first column of dp array. dp[i][0] = i, as we need i deletions to convert word1[0...i-1] to empty string.
// 6. Iterate through the dp array and fill it based on the following conditions:
//    a. If word1[i-1] == word2[j-1], then dp[i][j] = dp[i-1][j-1].
//    b. If word1[i-1] != word2[j-1], then dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]).
// 7. Finally, return dp[m][n] which indicates the minimum number of operations required to convert word1 to word2.

class Solution {
    public:
        int minDistance(string word1, string word2) {
            int m = word1.size();
            int n = word2.size();
            vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
            //first row will be 0 to no of colums
            for(int j = 1; j <= n; j++){
                dp[0][j] = j;
            }
            //first col will be 0 to number of rows
            for(int i = 1; i <= m; i++){
                dp[i][0] = i;
            }
    
            for(int i = 1; i <= m; i++){
                char w1 = word1[i - 1];
                for(int j = 1; j <= n; j++){
                    char w2 = word2[j - 1];
                    //if characters match, take top left
                    if(w1 == w2){
                        dp[i][j] = dp[i - 1][j - 1];
                    }else{//else take min between top, left and top left
                        dp[i][j] = 1 + min(dp[i - 1][j], min(dp[i - 1][j - 1], dp[i][j - 1]));
                    }
                }
            }
            return dp[m][n]; 
        }
    };