/**
Time Complexity - O(m * n)
Space Complexity - O(m * n)
 */
class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null && p == null)
            return true;
        if(s == null || p == null)
            return false;
        
        int slen = s.length(), plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[0][0] = true;

        for(int j = 0; j < plen; j++)
            if(p.charAt(j) == '*' && dp[0][j - 1])
                dp[0][j + 1] = true; 
        
        for(int i = 0; i < slen; i++) {
            for(int j = 0; j < plen; j++) {
                char src = s.charAt(i), tar = p.charAt(j);
                if(src == tar || tar == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if(tar == '*') {
                    char tarPrev = p.charAt(j - 1);
                    if(tarPrev != src && tarPrev != '.') 
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];  
                    else 
                        dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1];
                } 
            }
        }
        return dp[slen][plen];
    }
}
