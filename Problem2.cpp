class Solution {
// Time Complexity: O(n1 * n2)
// Space Complexity: O(n1 * n2)
public:
   bool solve(string &s , string &p , int i , int j , vector<vector<int>> &dp){

        if(i==0 && j == 0){
            return true;
        }
        else if(j==0 && i>0){
            return false;
        }
        else if(j>0 && i==0){
            if(p[j-1]=='*') return dp[i][j] = solve(s,p,i,j-2,dp);

            else return 0;
        }
        else if(dp[i][j]!=-1){
            return dp[i][j];
        }

        if(s[i-1] == p[j-1] || p[j-1] == '.'){
            return dp[i][j] = solve(s , p, i-1 , j-1, dp);
        }
        else if(p[j-1] =='*'){
            if(solve(s,p,i,j-2,dp)) return dp[i][j] =true;
            if(p[j-2]==s[i-1] || p[j-2]=='.') return dp[i][j] =solve(s,p,i-1,j,dp);
            return dp[i][j] =false;
        }
        return dp[i][j] = false;
}

    bool isMatch(string s, string p) {
        int n1 = s.length();
        int n2 = p.length();
        vector<vector<int>> dp(n1+1 , vector<int>(n2+1 , -1));
        return solve(s , p , n1, n2 , dp);
    }
};