// https://leetcode.com/problems/regular-expression-matching/description/
// Time Complexity: O(n*m)
// Space Complexity: O(m)

class Solution {
    public:
        bool isMatch(string s, string p) {
            int n = s.length(); int m = p.length();
            vector<bool> dp(m+1, false);
            dp[0] = true;
            for(int i=1; i<=m; i++){
                char ch = p[i-1];
                if(ch == '*'){
                    dp[i] = dp[i-2];
                }
            }
            for(int i=1; i<=n; i++){
                bool prev = dp[0];
                dp[0] = false;
                for(int j=1; j<=m; j++){
                    bool temp = dp[j];
                    char schar = s[i-1]; char pchar = p[j-1];
                    if(pchar != '*'){
                        if(schar == pchar || pchar == '.') dp[j] = prev; 
                        else dp[j] = false;           
                    }
                    else{
                        if(schar == p[j-2] || p[j-2] == '.') dp[j] = dp[j-2] || temp;
                        else dp[j] = dp[j-2];
                    }
                    prev = temp;
                }
            }
            return dp[m];
        }
    };