// Time Complexity : O(n*m) where n and m are lengths of string s and p
// Space Complexity : O(n*m)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Add black to first place in the string and regular expression this will act as blank
// Create a boolean dp[][] matrix of dimension length of s and p
// The base condition when either is empty can be filled directly
// Now we will iterate over the matrix solve each subproblem
// We will consider the equal case, the star and when not matching
// The final answer we can get form the last cell of the matrix
class Solution {
    public boolean isMatch(String s, String p) {
        s = " "+ s;
        p = " " + p;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        dp[0][1] = false;
        for(int i = 2; i < n; i++){
            if(p.charAt(i) == '*')
                dp[0][i] = dp[0][i-2];
        }
        for(int i = 1; i < m; i++){
            char c1 =  s.charAt(i);
            for(int j = 1; j < n; j++){
                char c2 = p.charAt(j);
                //match
                boolean equal = (c1 == c2) || (c2 == '.');
                if(c2 == '*'){
                    boolean prevEqual = (c1 == p.charAt(j-1)) || (p.charAt(j-1) == '.');
                    boolean consider = dp[i-1][j] && prevEqual;
                    boolean notConsider = dp[i][j-2];
                    dp[i][j] = consider || notConsider;
                        
                }
                else{
                    dp[i][j] = equal && dp[i-1][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}