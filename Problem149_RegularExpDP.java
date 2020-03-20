//Time Complexity: O(mn)

class Solution{
    public boolean isMatch(String s, String p) {
    //base case
    if (s == null || p == null) {
        return false;
    }
    boolean[][] dp = new boolean[s.length()+1][p.length()+1];
    //initially set [0,0] as true
    dp[0][0] = true;
    //for patterns like a*, a*b*, a*b*c*
    for (int i = 0; i < p.length(); i++) {
        if (p.charAt(i) == '*' && dp[0][i-1]) {
            dp[0][i+1] = true;
        }
    }
    
    for (int i = 0 ; i < s.length(); i++) {
        for (int j = 0; j < p.length(); j++) {
            //case1: if pattern has '.'
            //take the value of [i-1][j-1] location
            if (p.charAt(j) == '.') {
                dp[i+1][j+1] = dp[i][j];
            }
            //case2: if string character and pattern character is equal
            //take the value of [i-1][j-1] location
            if (p.charAt(j) == s.charAt(i)) {
                dp[i+1][j+1] = dp[i][j];
            }
            //case3: if pattern has '*'
            //there are 2 cases; consider the character or don't consider
            if (p.charAt(j) == '*') {
                if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                    dp[i+1][j+1] = dp[i+1][j-1];
                } else {
                    dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                }
            }
        }
    }
    //return last value of the grid
    return dp[s.length()][p.length()];
    }
}
