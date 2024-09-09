// 9/4 class

//once think with below soln, what if there are 2 consecutive * or * at the beginning

/*
dp:
take dummy row and col
first col -> false, except 0,0 element
first row -> false, except 0,0 and when char is * -> 2 steps back
if curr chars match -> diagonally up left
if curr char is * -> if prev chars match -> 2 steps behind || up
                     if prev chars do not match -> 2 steps back
*/

//time: O(mn)
//space: O(mn)
/*
class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.length();
        int n = p.length();
        vector<vector<bool>> dp(m+1, vector<bool>(n+1));
        dp[0][0] = true;
        //top row
        for(int j = 1; j <= n; j++) {
            if(p[j-1] == '*') {
                dp[0][j] = dp[0][j-2];
            }
        }
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(p[j-1] == '*') {
                    if(p[j-2] == s[i-1] || p[j-2] == '.') {
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    }
                    else {
                        dp[i][j] = dp[i][j-2];
                    }
                }
                else {
                    if(p[j-1] == s[i-1] || p[j-1] == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[m][n];
    }
};
*/

//space: O(n)
class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.length();
        int n = p.length();
        vector<bool> dp(n+1);
        dp[0] = true;
        //top row
        for(int j = 1; j <= n; j++) {
            if(p[j-1] == '*') {
                dp[j] = dp[j-2];
            }
        }
        for(int i = 1; i <= m; i++) {
            bool diagUp = dp[0];
            dp[0] = false;
            for(int j = 1; j <= n; j++) {
                bool temp = dp[j];
                if(p[j-1] == '*') {
                    if(p[j-2] == s[i-1] || p[j-2] == '.') {
                        dp[j] = dp[j-2] || dp[j];
                    }
                    else {
                        dp[j] = dp[j-2];
                    }
                }
                else {
                    if(p[j-1] == s[i-1] || p[j-1] == '.') {
                        dp[j] = diagUp;
                    }
                    else {
                        dp[j] = false;
                    }
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
};