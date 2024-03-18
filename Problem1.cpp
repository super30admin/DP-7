class Solution {
// Time Complexity: O(m * n)
// Space Complexity: O(m * n)
public:
    int minDistance(string word1, string word2) {
        int m = word1.length();
        int n = word2.length();
        
       vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));

        return helper(word1, word2, m - 1, n - 1, dp);
    }
      int helper(string& word1, string& word2, int index1, int index2, vector<vector<int>>& dp) {
    // Base case, both strings are exhausted
        if (index1 < 0 && index2 < 0) return 0;
        // index2 is not < 0, so we need index2 + 1 insertions to convert word1 to word2
        if (index1 < 0) return index2 + 1;
        // index1 is not < 0, so we need index1 + 1 deletions to convert word1 to word2
        if (index2 < 0) return index1 + 1;
        if (dp[index1][index2] != 0) return dp[index1][index2];

        // If both characters are the same, no operation is required
        if (word1[index1] == word2[index2])
            return dp[index1][index2] = helper(word1, word2, index1 - 1, index2 - 1, dp);
        else {
            // Case 1: Insert operation
            int insert = 1 + helper(word1, word2, index1, index2 - 1, dp);
            // Case 2: Delete operation
            int deleteOp = 1 + helper(word1, word2, index1 - 1, index2, dp);
            // Case 3: Replace operation
            int replace = 1 + helper(word1, word2, index1 - 1, index2 - 1, dp);
            return dp[index1][index2] = min({ insert, deleteOp, replace });
        }
}
};