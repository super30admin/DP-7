// Time Complexity :o(mn)
// Space Complexity :o(mn)
// Did this code successfully run on Leetcode :no
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    public boolean isMatch(String s, String p) {

        boolean[][] dp=new boolean[s.length()+1][p.length()+1];
        dp[0][0]=true;
        for(int m=1;m<=p.length();m++){
            if(p.charAt(m-1)=='*'){
                dp[0][m]=dp[0][m-2];
            }else{
                dp[0][m]=false;
            }
            
        }
        
        
        for(int i=0;i<=p.length();i++){
        System.out.println(dp[1][i]);}
        
        for(int n=1;n<=s.length();n++){
            dp[n][0]=false;
        }
        
        //   for(int i=0;i<=s.length();i++){
        // System.out.println(dp[i][0]);}
        
        for(int i=1;i<=s.length();i++){
         for(int j=1;j<=p.length();j++){
             if(p.charAt(j-1)==s.charAt(i-1) || p.charAt(j-1)=='.'){
                dp[i][j]=dp[i-1][j-1]; 
             }
             if(p.charAt(j-1)=='*'){
                 dp[i][j]=dp[i][j-2];
                 if(p.charAt(j-2) ==s.charAt(i-1) || p.charAt(j-2)=='.'){
                     dp[i][j]=dp[i][j] || dp[i-1][j];
                 }
             }
         }
         }
        
        return dp[s.length()][p.length()];
    }
}