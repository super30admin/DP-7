//Time - O(mxn)
//Space - O(mxn)
class Solution {
public:
    bool isMatch(string s, string p) {
        int sLen = s.size(), pLen = p.size();

        vector<vector<bool>> dp (s.size()+1, vector<bool>(p.size()+1, false));

        dp[0][0] = true;

        for(int i = 1;i<=pLen;i++){
            if(isalpha(p[i-1]) || p[i-1] == '.') dp[0][i] = false; //this is not needed default is false
            else if(p[i-1] == '*') dp[0][i] = dp[0][i-2]; 
        }
        
        for(int i = 1;i<=sLen;i++){
            for(int j=1;j<=pLen;j++){
                if(p[j-1]!='*'){
                    if(p[j-1] == '.' || s[i-1] == p[j-1]) dp[i][j] = dp[i-1][j-1];
                    else if(s[i-1] != p[j-1]) dp[i][j] = false; // this is not needed default is false
                }else{
                    if(p[j-2] == s[i-1] || p[j-2] == '.') dp[i][j] = (dp[i-1][j] || dp[i][j-2]);
                    else if(p[j-2] != s[i-1]) dp[i][j] = dp[i][j-2];
                }
            }
        }
        
        return dp[sLen][pLen];
    }
};