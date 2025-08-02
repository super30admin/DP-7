// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Approach : Use DP. For each character in pattern check if it matches char in source. If yes, check if without current
/// character if so far sourc ematches pattern. If pattern has *, check if charcter prev to it matches character in pattern. If yes
/// we can choose that char, else no.

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean dp[][] = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int j=1;j<=n;j++){
            if(p.charAt(j-1)=='*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                char sch = s.charAt(i-1);
                char pch = p.charAt(j-1);
                if(pch != '*'){
                    if(pch == sch || pch == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else{
                        dp[i][j] = false;
                    }
                }
                else{
                    char prevCh = p.charAt(j-2);
                    if(sch == prevCh || prevCh == '.'){
                        dp[i][j] = dp[i-1][j] || dp[i][j-2];
                    }
                    else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return dp[m][n];
    }
}