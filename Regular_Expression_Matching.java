// Time Complexity : O(M*N)
// Space Complexity : O(M*N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public boolean isMatch(String s, String p) {
        int n=p.length();
        int m=s.length();
        boolean[][] dp=new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int j=1;j<n+1;j++){
            if(p.charAt(j-1)=='*'){
                dp[0][j]=dp[0][j-2];
            }
        }
        
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                char pchar=p.charAt(j-1);
                char schar=s.charAt(i-1);
                if(pchar!='*' && (pchar==schar || pchar=='.')){
                    dp[i][j]=dp[i-1][j-1];
                }else if(pchar=='*'){
                    boolean notTaken = dp[i][j]=dp[i][j-2];
                    boolean taken=false;
                    if(p.charAt(j-2)==schar || p.charAt(j-2)=='.'){
                        taken=dp[i-1][j] ;
                    }
                        dp[i][j]=notTaken || taken;
                    
                }
                
            }
            
        }
        
        return dp[m][n];
        
    }
}