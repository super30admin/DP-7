// Time Complexity : O(m*n)
// Space Complexity : O(m*n) 
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this : NO

// Your code here along with comments explaining your approach
class Solution {
    
    public boolean isMatch(String s, String p) {
        
        int n = s.length();
        int m = p.length();
        
        boolean[][]dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        
        for(int i = 1;i<=m;i++) {
            if(p.charAt(i-1)=='*') {
                dp[0][i] = dp[0][i-2];
            }
        }
        
        for(int i = 1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                
                char schar = s.charAt(i-1);
                char pchar = p.charAt(j-1);
                
                if(schar == pchar || pchar == '.') {
                    dp[i][j] = dp[i-1][j-1];
                }
                else if(schar != pchar && pchar!='*') {
                    dp[i][j] = false;
                } 
                else {
                    
                    boolean dc = dp[i][j-2];
                    boolean c = false;
                    
                    if(schar == p.charAt(j-2) || p.charAt(j-2) == '.') {
                        c = dp[i-1][j];
                    }
                    
                    dp[i][j] = c || dc;
                    
                }
                
            }
        }
        
        return dp[n][m];
        
    }
    
}
