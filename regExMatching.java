// Time Complexity : O(MN)
// Space Complexity : O(MN)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


public class regExMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length(); 
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        for(int i = 0; i <= m; i++){
            for(int j = 0;j <= n; j++){
                if(i == 0 && j == 0){ 
                    dp[i][j] = true;
                }else if(j == 0){  
                    dp[i][j] = false;
                }else if(i == 0){ 
                    if(j-2 >= 0 && p.charAt(j-1) == '*'){  
                        dp[i][j] = j-2 >=0 ? dp[i][j-2] : true; 
                    }else{
                        dp[i][j] = false;
                    }
                }else if(j-2 >= 0 && p.charAt(j-1) == '*'){ 

                       dp[i][j]=  ((s.charAt(i-1)==p.charAt(j-2) || p.charAt(j-2)=='.') && (dp[i-1][j] || dp[i-1][j-2]) )|| dp[i][j-2];

                }else if(p.charAt(j-1)==s.charAt(i-1) || p.charAt(j-1)=='.'){ 
                    dp[i][j]=dp[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }
}
