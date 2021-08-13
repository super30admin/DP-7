// Time Complexity : O (slen*plen)
// Space Complexity :O (slen*plen)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach


class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;
        int slen=s.length();
        int plen=p.length();
        boolean[][] dp=new boolean[slen+1][plen+1];
        
        //- and - is true
        dp[0][0]=true;
        
        //first row
        for(int col=1;col<dp[0].length;col++){
            //if * copy from col-2 in the same row
            if(p.charAt(col-1)=='*'){
                 dp[0][col]=dp[0][col-2];
            }
        }
        
        //remaining rows
        for(int row=1;row<dp.length;row++){
            for(int col=1;col<dp[0].length;col++){
                if(p.charAt(col-1) != '*'){
                    // if char is same copy from above row diagnol
                    if(p.charAt(col-1)==s.charAt(row-1) || p.charAt(col-1)=='.'){
                        dp[row][col]=dp[row-1][col-1];
                    }
                }else{
                    //default to same row col-2
                    dp[row][col]=dp[row][col-2];
                    
                    //if cur char is * check preceding char
                    if(p.charAt(col-2)==s.charAt(row-1) || p.charAt(col-2)=='.'){
                        dp[row][col]=dp[row][col]||dp[row-1][col];
                    }
                }
            }
        }
        return dp[slen][plen];
    }
}