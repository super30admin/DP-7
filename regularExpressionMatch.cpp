// Time Complexity : O(m*n) 
// Space Complexity : O(m*n) 
// Did this code successfully run on Leetcode : Yes 

//dp solution
/*
3 main steps to the algo 
1. if its not *
    Just check if the letters match, if they do, copy over the solution from diagonal (i.e., remove that char from both s and p)
    dp[i][j] = dp[i-1][j-1]

2. *
    here we have 2 scenarios - case 0 and case 1
    case 0: 
        we are not picking the character* combo. So copy over the solution from 2 rows behind 
        (because last 2 chars from pattern is ignored)

    case 1: 
        where we consider a letter. If that letter is same as the input, we will remove that letter from both s and p
        note that a* = a*a 
        after cancelling, a*a => a* 
        so 1 letter from input is gone and the pattern is still the same a*
        this answer is right above the current block in dp
*/
class Solution {
public:
    bool isMatch(string s, string p) {
        if(s==p) return true;
        int m = s.length();
        int n = p.length();

        vector<vector<bool>> dp (m+1, vector<bool>(n+1, false));
        
        //empty pattern and empty strings
        dp[0][0] = true;
        
        //initialise the first row
        //the input string is empty - so the only time we can create an empty string is if there are *
        // if * is there, we remove char-* combination, thus copying over the answer from 2 rown behind 
        for(int i = 1; i<n+1; i++){
            if(p[i-1] == '*')
                dp[0][i] = dp[0][i-2];
        }
        
        // filling the rest of the grid
        for(int i = 1; i<m+1; i++){
            for(int j = 1; j<n+1; j++) {
                // case where its a char or .
                // have to pick it - there is no case 0
                if(p[j-1] != '*') {
                    // cancelling both the matches letter and pattern and get the value from i-1, j-1
                    if(p[j-1] == s[i-1] || p[j-1] == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                // if its * 
                // case 0 - don't pick and the patter changes to j-2
                // case 1 - pick and the input string changes to i-1
                else {
                    dp[i][j] = dp[i][j-2];
                    if(p[j-2] == s[i-1] || p[j-2] == '.') {
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
};