// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
/*
 * In this top down approach, if the chars match or its a . in pattern, then we increment pointer in both strings.
 * If there is a * in pattern, then we have two options either skip it or use to more than 1 time.
 * Use memoization, to store the functional call values in memo array and reuse it whever its necessary.
 */

import java.util.Arrays;

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        int[][] memo = new int[m][n];
        for(int[] mem : memo){
            Arrays.fill(mem, -1);
        }
        return helper(s, p, 0, 0, memo);
    }

    private boolean helper(String s, String p, int i, int j, int[][] memo){
        if(i == s.length() && j == p.length()) return true;
        
        if(j == p.length()) return false;

        if(i == s.length()){
            while(j < p.length()){
                if(j+1 < p.length() && p.charAt(j+1) == '*'){
                    j += 2;
                }else {
                    return false;
                }
            }
            return true;
        }

        if(memo[i][j] != -1){
            return memo[i][j] == 1 ? true : false;
        }

        boolean match = s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';

        if(j+1 < p.length() && p.charAt(j+1) == '*'){
            memo[i][j] = helper(s, p, i, j+2, memo) || (match && helper(s, p, i+1, j, memo)) ? 1 : 0;
            return helper(s, p, i, j+2, memo) || (match && helper(s, p, i+1, j, memo));
        }

        if(match){
            memo[i][j] = helper(s, p, i+1, j+1, memo) ? 1 : 0;
            return helper(s, p, i+1, j+1, memo);
        }

        memo[i][j] = 0;
        return false;
    }
}