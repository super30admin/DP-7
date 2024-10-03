/*
**Time Complexity (TC):** O(N * M)

**Space Complexity (SC):** O(N * M)

Where:
- `N` is the length of the string `s`.
- `M` is the length of the string `t`.
*/

class Solution {
public:
    int helper(string s, string t, int i, int j, vector<vector<int>> &dp) {
        if(s.length() == i && t.length() == j) {
            return dp[i][j] = 0;
        } else if(i < s.length() && t.length() == j) {
            return dp[i][j] = s.length() - i;
        } else if(i == s.length() && j < t.length()) {
            return dp[i][j] = t.length()-j;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        if(s[i] == t[j]) {
            return dp[i][j] = helper(s,t,i+1,j+1, dp);
        }
        return dp[i][j] = 1+min(min(helper(s,t,i,j+1, dp), helper(s,t,i+1,j, dp)), helper(s,t,i+1,j+1, dp));
    }
    int minDistance(string s, string t) {
        vector<vector<int>> dp(s.length()+1, vector<int>(t.length()+1, -1));
        return helper(s, t, 0 ,0, dp);
    }
};