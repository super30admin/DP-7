// Time Complexity : O(nXm) n,m = lengths of pattern and source
// Space Complexity : O(nXm) n,m = lengths of pattern and source
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    public boolean isMatch(String s, String p) {

        int sl = s.length(); //rows of dp matrix
        int pl = p.length(); //cols of dp matrix

        //dp matrix, all false by default
        boolean[][] dp = new boolean[sl+1][pl+1];

        //empty string will always match empty string
        dp[0][0] = true;

        //fill in first row
        for(int j=1; j<dp[0].length; j++)
        {
            //if it is a letter, will never match empty string, so false (default)

            //if it is a *, we find the solution 2 steps back
            if(p.charAt(j-1) == '*') //j-1 because we appended empty string case before pattern/source start
                dp[0][j] = dp[0][j-2];
        }

        //fill in rest of the matrix
        for(int i=1; i<dp.length; i++)
        {
            for(int j=1; j<dp[0].length; j++)
            {
                //action character is a letter or a .
                if(p.charAt(j-1) != '*')
                {
                    //if letters match or there is a dot, answer is diagonal up left
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.')
                        dp[i][j] = dp[i-1][j-1];
                }
                else //action char is a *
                {
                    //fill cell with zero case - two steps back, same row
                    dp[i][j] = dp[i][j-2];

                    //one case - it exists if char in s matches the char in p preceeding *
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.')
                        dp[i][j] = dp[i][j] || dp[i-1][j]; // zero case || one case
                }
            }
        }

        return dp[dp.length-1][dp[0].length-1]; //ans is last cell of matrix
    }
}
