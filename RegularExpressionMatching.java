// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We check if the character of s and i match together recursively and memoize the results. During match process,
if the character is '.', its still a match as it can take any character. If its '*', we compute choose and
no choose cases by moving i, j pointers and call helper function recursively. IF not, we just increment the
i, j pointers and recurse.
 */
class Solution {
    Boolean[][] memo;
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        this.memo = new Boolean[m + 1][n + 1];
        return helper(s, p, 0, 0);
    }

    private boolean helper(String s, String p, int i, int j) {
        if(i == s.length() && j == p.length())
            return true;
        if(j == p.length())
            return false;
        if(memo[i][j] != null)
            return memo[i][j];

        boolean result = false;

        boolean firstMatch = (i < s.length()) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        if(j + 1 < p.length() && p.charAt(j + 1) == '*') {
            //no-choose, choose
            result = helper(s, p, i , j + 2) || (firstMatch && helper(s, p, i + 1, j));
        }
        else {
            if(firstMatch) {
                result = helper(s, p, i + 1, j + 1);
            }
        }

        memo[i][j] = result;
        return result;
    }
}