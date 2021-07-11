// Time Complexity :O(nm) n & m are the size of the strings s & p
// Space Complexity : O(nm)   
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class Solution {
public:
    bool isMatch(string s, string p) {
        int sl = s.length();
        int pl = p.length();
        vector<vector<bool>> dp(sl+1,vector<bool>(pl+1,false));
        //top row
        dp[0][0] = true;
        for(int j = 1;j < dp[0].size();j++){
            if(p[j-1] == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        for(int i = 1;i<dp.size();i++){
            for(int j = 1; j < dp[0].size();j++){
                if(p[j-1] != '*'){
                    if(p[j-1] == s[i-1] || p[j-1] == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else{
                    //zero case
                    dp[i][j] = dp[i][j-2];
                    //one case 
                    if(p[j-2] == s[i-1] || p[j-2] == '.' )
                        dp[i][j] = dp[i][j] or dp[i-1][j];
                }
            }
        }
        return dp[sl][pl];
    }
};