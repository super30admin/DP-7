//dp
//for the path, we will have to store where we came from in the matrix -> cannot do it in O(n) space in that case

//time: O(mn)
//space: O(n)
class Solution {
public:
    int minDistance(string word1, string word2) {
        int m = word1.length();
        int n = word2.length();
        vector<int> dp(n+1);
        //top row
        for(int j = 0; j <= n; j++) {
            dp[j] = j;
        }
        for(int i = 1; i <= m; i++) {
            int diagUp = dp[0];
            dp[0] = i;
            for(int j = 1; j <= n; j++) {
                int temp = dp[j];
                if(word1[i-1] == word2[j-1]) {
                    dp[j] = diagUp;
                }
                else {
                    dp[j] = 1 + min({dp[j-1], diagUp, dp[j]});
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
};