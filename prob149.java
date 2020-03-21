// 10. Regular Expression Matching
// S30 Big N Problem #149 {Hard}
// Time Complexity : O(m*n) where m,n are the lengths of corresponding strings 
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :None


// Your code here along with comments explaining your approach
class Solution {
    public boolean isMatch(String s, String p) {
        //edge case
        if(p==null) return s==null;
        int m=s.length();
        int n=p.length();
        boolean [][]dp=new boolean[m+1][n+1];
        dp[0][0]=true;
        
        //first row
        for(int j=1;j<=n;j++){
            if(p.charAt(j-1)=='*'){
                dp[0][j]=dp[0][j-2];
            }
        }
        
        //filling remaining indices
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                //case 1
                if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1)=='.'){
                    dp[i][j]=dp[i-1][j-1];
                } else if(p.charAt(j-1)=='*'){//character is *
                    dp[i][j]=dp[i][j-2];
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2)=='.'){
                        if(dp[i][j-2] || dp[i-1][j]){//upper index
                            dp[i][j]=true;
                        }
                    }    
                }
            }
        }
        return dp[m][n]; 
    }
}