/*
**Time Complexity (TC):** O(N * M)

**Space Complexity (SC):** O(N * M)

Where:
- `N` is the length of the string `s`.
- `M` is the length of the string `p`.
*/

class Solution {
public:
    string removeMultipleStars(string &p) {
        if(p.length()==0) return p;
        string ans="";
        ans += p[0];
        for(int i=1;i<p.length();i++) {
            if(ans.back() == '*' && p[i] == '*') {
                continue;
            } else {
                ans+=p[i];
            }
        }
        return ans;
    }
    int helper(string &s, string &p, int n, int m, vector<vector<int>> &dp) {
        if(n==0 && m==0) {
            return dp[n][m] = 1;
        }
        if(m==0) return dp[n][m]=0;
        if(dp[n][m]!=-1) return dp[n][m];
        
        char ch1=n>0 ? s[n-1] : '$';
        char ch2=p[m-1];        
        if(ch1  != '$' && (ch1 == ch2 || ch2 == '.')) {
            return helper(s,p,n-1,m-1,dp);
        } else if(ch2 == '*') {
            bool res = false;
            if(n > 0 && m > 1 && (p[m-2] == '.' || p[m-2] == ch1)) {
                res = res || helper(s,p,n-1,m,dp) == 1;
            }
            res = res || helper(s,p,n,m-2,dp) == 1;
            return dp[n][m] = res == true ? 1 : 0;
        }
        return dp[n][m] = 0;
    }
    bool isMatch(string s, string p) {
        
        p=removeMultipleStars(p);
        vector<vector<int>> dp(s.length()+1,vector<int>(p.length()+1,-1));
        
        cout << p << endl;
        return helper(s,p,s.length(),p.length(),dp) == 1;
    }
};