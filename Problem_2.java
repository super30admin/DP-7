// Time Complexity : O(m*n)
// Space Complexity :O(m*n)
// Did this code successfully run on Leetcode : Yes
// Three line explanation of solution in plain english
// if not * then take it from the horizontal top, if is * then zero one case for zero: take it from 2 step back, for one take or between old value and one above if both the character is same
// Your code here along with comments explaining your approach
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length(); int pl = p.length();
        if(s.equals(p)) return true;
        boolean [][] dp = new boolean [sl+1][pl+1];
        dp[0][0] = true;
        // top row
        for(int j = 1; j < dp[0].length; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        // main
        for( int i = 1; i < dp.length; i++){
            for( int j = 1; j < dp[0].length; j++){
                if(p.charAt(j-1) != '*'){
                    // if char or '.'
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }else{
                    // zero case
                    dp[i][j] = dp[i][j-2];
                    // one case
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[sl][pl];
    }
}