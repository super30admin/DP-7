// Time Complexity : O(M*N)
// Space Complexity : O(M*N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    public boolean isMatch(String s, String p) {
        int m = p.length();
        int n = s.length();
        boolean[][]dp = new boolean[m+1][n+1];
        for(int i = 0 ; i < m+1 ; i++){
            for(int j = 0 ; j < n+1 ; j++){
                if(i == 0 && j == 0) dp[i][j] = true;
                else if(i == 0) continue;
                else if(j == 0){
                    if('*' == p.charAt(i-1))    dp[i][j] = dp[i-2][j];
                    }    
                else{
                    char a = p.charAt(i-1);
                    char b = s.charAt(j-1);
                    if(a == b) dp[i][j] = dp[i-1][j-1];
                    else if(a == '*'){
                        char temp = p.charAt(i-2);
                        if(temp == '.' || temp == b) dp[i][j] = dp[i-2][j] || dp[i][j-1];
                        else dp[i][j] = dp[i-2][j];
                    }
                    else if(a == '.') dp[i][j] = dp[i-1][j-1];
                    
                }
                
            }
        }
        return dp[m][n];        
    }
}

