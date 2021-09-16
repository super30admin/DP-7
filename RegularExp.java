// Time Complexity : o(m*n)
// Space Complexity : o(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    public boolean isMatch(String s, String p) {
      
        if(s.equals(p))
            return true;
        
        int slength=s.length();
        int plength=p.length();
        
        boolean dp[][]=new boolean[slength+1][plength+1];
        
        dp[0][0]=true;
        
        // top row of empty string
        for(int i=1;i<dp[0].length;i++)
        {
            if(p.charAt(i-1)=='*')
            {
                dp[0][i]=dp[0][i-2];
            }
        }
        
        //fill array
        for(int i=1;i<dp.length;i++)
        {
            for(int j=1;j<dp[0].length;j++)
            {
                
                if(p.charAt(j-1)!='*')
                {
                    //ordinary character
                    
                    if(p.charAt(j-1)=='.'|| p.charAt(j-1)==s.charAt(i-1))
                    {
                        // matching or not
                        
                        dp[i][j]=dp[i-1][j-1]; // copy from diagonal
                    }
                }
                else
                {
                    dp[i][j]=dp[i][j-2];
                    //0 and 1 case
                    //available 1 case
                    
                    if(s.charAt(i-1)== p.charAt(j-2) || p.charAt(j-2)=='.')
                    {
                        dp[i][j]=dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[slength][plength];
    }
}