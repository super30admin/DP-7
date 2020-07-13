// Time Complexity : O(mn) where m is the number of rows and n is the number of columns in the matrix
// Space Complexity : O(mn) dp matrix
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Difficult to uncover the pattern here
/* Your code here along with comments explaining your approach: We have different cases here. In one case we have to take care if we get equal
character or a dot character it means that the pattern would surely match to the string and hence additional character adds no value, we would
use the diagonal result. Else if we encounter a *, we have two options: choose or not choose.If we ignore the * not choose => we would get
a subproblem two steps back when * was not existant, but if we choose the *, we need to see if we are able to form the pattern by checking
if the previous character of the pattern and the string match, if yes, then use true result else simply use the two step back result.
*/
class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return true;
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for(int i = 1; i <dp[0].length; i++)
        {
            if(i > 1 && p.charAt(i-1) == '*')
                dp[0][i] = dp[0][i-2];                                                                          // If it is *, use the result two steps back
        }
        
        for(int i  =  1; i< dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){                                  // If the characters are same, use the diagonal result
                    dp[i][j] = dp[i-1][j-1];
                } else{
                if(j > 1 && p.charAt(j-1) == '*'){                                                          // If we encounter a  *
                    dp[i][j] = dp[i][j-2];                                                                          // 0 case, use two step back
                 if((s.charAt(i-1) == p.charAt(j-2)) || p.charAt(j-2) == '.'){                          // 1 case, check if 0 case is true or check the previous character, if either is true
                            if(dp[i][j] == true || dp[i-1][j] == true)
                                dp[i][j] = true;
                        }                                                                                   // if characters are different, it will be false by default
                }       
                }   
                }
                }
        return dp[dp.length-1][dp[0].length-1];                                                                 // Return the optimal result
    }
}