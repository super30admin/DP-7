// Time Complexity: O(m*n)
// Space Complexity: O(m*n)
// Did you run the code on Leetcode? Yes

//Approach:
//1. Create a 2D dp array of size (m+1)*(n+1) where m is the length of s and n is the length of p.
//2. dp[i][j] will be true if s[0...i-1] matches p[0...j-1].
//3. Initialize dp[0][0] = true, as empty string matches empty pattern.
//4. Fill the first row of dp array. If p[j-1] is '*', then dp[0][j] = dp[0][j-2].
//5. Iterate through the dp array and fill it based on the following conditions:
//   a. If p[j-1] is '*', then dp[i][j] = dp[i][j-2] or (if s[i-1] matches p[j-2]) dp[i][j] = dp[i][j] || dp[i-1][j].
//   b. If s[i-1] matches p[j-1] or p[j-1] is '.', then dp[i][j] = dp[i-1][j-1].
//6. Finally, return dp[m][n] which indicates if s matches p.

class Solution {
    public:
        bool isMatch(string s, string p) {
            int m = s.size();
            int n = p.size();
            vector<vector<bool>>dp(m+1, vector<bool>(n+1, false));
            dp[0][0] = true;
            //Filling first row
            for(int j = 2; j <= n; j++){
                char c = p[j - 1];
                if(c == '*'){
                    dp[0][j] = dp[0][j - 2];
                }
            }
            for(int i = 1; i <= m; i++){
                char sCurr = s[i - 1];
                for(int j = 1; j <= n; j++){
                    char pCurr = p[j - 1];
                    //if p has *
                    if(pCurr == '*'){
                        dp[i][j] = dp[i][j - 2];
                        if(p[j - 2] == sCurr || p[j - 2] == '.'){
                            dp[i][j] = dp[i][j] || dp[i - 1][j];
                        }
                    }
                    //if last characters match or they are ., then get top left
                    else if(sCurr == pCurr || pCurr == '.'){
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
            return dp[m][n];
        }
    };