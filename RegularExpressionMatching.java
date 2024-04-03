// Time Complexity :O(N1*N2)
// Space Complexity :O(N1*N2)
// Did this code successfully run on Leetcode :Yess
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach: Tabulation DP
class Solution {
    public boolean isMatch(String s, String p) {
        int n1 = s.length();
        int n2 = p.length();
        boolean dp[][]= new boolean[n1+1][n2+1];
        dp[0][0]= true;
    
        for (int j = 1; j <= n2; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for(int i=1; i<=n1; i++){
            for(int j=1; j<=n2;j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if(p.charAt(j-1) == '*') {
                    dp[i][j] =  dp[i-1][j] || dp[i][j - 1];
                } 
            }
        }
        return dp[n1][n2];
    }
}
