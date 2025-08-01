// Time complexity - O(m*n)
// Space complexity - O(m*n)
// Solved on leetcode - yes
// Faced any issues - No
// For the given pattern, if a character in pattern doesn't match with the character in string updating as false
// If the current character matches need to check for the previous characters - we can take the top left value
// If the character is * we have 2 cases - 0 and 1 case - for 0 case we can take 2 steps back value in current row, If one case exists, then get the top element
class Solution {
    public boolean isMatch(String s, String p) {
        int slength = s.length(), plength = p.length();
        boolean dp[][] = new boolean[slength+1][plength+1];
        dp[0][0] = true;
        for(int j = 1; j <= plength; j++){
            char pChar = p.charAt(j - 1);
            if (pChar== '*'){
            dp[0][j] = dp[0][j-2];
            }
        }
        for(int i=1;i<slength+1; i++) {
            for(int j=1;j<plength+1;j++) {
                if(p.charAt(j-1) == '*') {
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') {
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i][j-2];
                    }
                } else {
                if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } //else {
                //     dp[i][j] = false;
                // }
                }
            }
        }
        return dp[slength][plength];
    }
}
