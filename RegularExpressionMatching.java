// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public boolean isMatch(String s, String p) {
        int r = s.length() + 1;
        int c = p.length() + 1;
        boolean dp[][] = new boolean[r][c];
        
        dp[0][0] = true;
        
        for(int j = 1; j < c; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        
        for(int i = 1; i < r; i++){
            for(int j = 1; j < c; j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }else if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i][j-2];
                    
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        if(dp[i][j-2] || dp[i-1][j]){
                            dp[i][j] = true;
                        }
                    }
                }
            }
        }
        return dp[r-1][c-1];
    }
}
