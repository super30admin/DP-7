public class Solution {

    // TC => O(m*n)
    // SC => O(m*n)
    public bool IsMatch(string s, string p) {
        if(s == p){
            return true;
        }

        int m = s.Length;
        int n = p.Length;
        bool[][] dp = new bool[m+1][];
        for(int i = 0; i< m+1; i++){
            dp[i] = new bool[n+1];
        }
        dp[0][0] = true;
        for(int j = 1; j < n+1; j++){
            if(p[j-1] == '*'){
                dp[0][j] = dp[0][j-2]; 
            }
        }

        for(int i = 1; i < m+1; i++){
            for(int j = 1; j< n+1; j++){
                if(s[i-1] == p[j-1] || p[j-1] == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(p[j-1] == '*'){
                    dp[i][j] = dp[i][j-2];
                    if(p[j-2] == s[i-1] || p[j-2] == '.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j]; 
                    } 
                }
            }
        }
        return dp[m][n];
    }
}