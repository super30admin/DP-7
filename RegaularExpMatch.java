// Time: O(MN) | Space: O(MN)
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        boolean[][] dp = new boolean[sl+1][pl+1];
        dp[0][0] = true;
        // only case of asterik we have two choices,
        // choose and not choose
        // if not choose, we should take two steps back value to ignore preceeding character
        // if choose, otherwise character match, we should take diagonal value
        // if no character match, then no change needed, it will remain false
        for(int i=1;i<dp[0].length;i++){
            if(p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++) {
                if(p.charAt(j-1) != '*') {
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.')
                        dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = dp[i][j-2];
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') dp[i][j] = dp[i][j] || dp[i-1][j];
                }
            }
        }
        return dp[sl][pl];
    }
}