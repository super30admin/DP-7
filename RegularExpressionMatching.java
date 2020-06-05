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

    public boolean isMatchDP(String s, String p) {
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

    // Class, Best Algorithm

    public boolean isMatch(String s, String p) {
        if(p == null){
            return s==null;
        }

        int pLength = p.length();
        int sLength = s.length();

        boolean[][] dp = new boolean[sLength+1][pLength+1];

        dp[0][0] = true;

        // if pattern consists of *, go 2 steps back and copy the value
        for(int i = 1; i < dp[0].length; i++){
            if(p.charAt(i-1) == '*'){
                dp[0][i] = dp[0][i-2];
            }
        }

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                // equal chars and not * , I have a character
                if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                } else if(p.charAt(j-1) == '*'){
                    // there is a *
                    // zero case
                    dp[i][j] = dp[i][j-2];

                    // one case
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        if(dp[i][j] || dp[i-1][j]){
                            dp[i][j] = true;
                        }
                    }
                }
            }
        }
        return dp[sLength][pLength];
    }

}
