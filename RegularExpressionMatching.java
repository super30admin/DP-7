// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public class RegularExpressionMatching {
    // Recursive BF
    public boolean isMatchBF(String s, String p) {
        if(p.length() == 0){
            return s.length() == 0;
        }

        boolean firstMatch = (s.length() != 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        if(p.length() >= 2 && p.charAt(1) == '*'){
            return (isMatchBF(s, p.substring(2)) || (firstMatch && isMatchBF(s.substring(1), p)));
        } else {
            return firstMatch && isMatchBF(s.substring(1), p.substring(1));
        }
    }

    // Dynamic Programming
    boolean[][] dp;

    public boolean isMatch(String s, String p) {
        dp = new boolean[s.length() + 1][p.length() + 1];
        return helper(0,0,s,p);
    }

    private boolean helper(int i, int j, String s, String p){
        boolean ans = false;
        if( j == p.length()){
            ans = ( i == s.length());
        } else {
            boolean firstMatch = (i < s.length() && ((p.charAt(j) == s.charAt(i)) || p.charAt(j) == '.'));
            if(j+1 < p.length() && p.charAt(j+1) == '*'){
                ans = (helper(i, j+2, s, p) || firstMatch && helper(i+1, j, s, p));
            } else {
                ans = firstMatch && helper(i+1, j+1, s, p);
            }
        }

        dp[i][j] = ans;
        return ans;
    }

}
