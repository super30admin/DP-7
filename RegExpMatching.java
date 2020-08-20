// Time Complexity - O(mn)
// Space Complexity - O(mn)
// Approach
// In one case we have to check if we get equal char or dot char it means that the 
// pattern would surely match to the string and hence additional char adds no value,
// we would use the diagonal result. Else if we come across a *, we have two choices: 
// to choose or not to choose. If we ignore * not choose -> we would get a subproblem two
// steps back when * was not there, but if we choose *, we need to see if we are able to 
// form the pattern by chcecking if prev char of pattern and string match, if yes, then use
// true result else simply use two step back result.

class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return true;
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for(int i=1;i<dp[0].length;i++) {
            if(p.charAt(i-1)=='*') {
                dp[0][i]=dp[0][i-2]; // use result that is two steps back
            }
        }
        for(int i=1;i<dp.length;i++) {
            for(int j=1;j<dp[i].length;j++) {
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1)=='.') { // characters same
                    dp[i][j] = dp[i-1][j-1]; // diagonal result
                } else {
                    if(j>1 && p.charAt(j-1)=='*') {
                        dp[i][j] = dp[i][j-2]; // use two steps back
                        if((s.charAt(i-1)==p.charAt(j-2)) || p.charAt(j-2)=='.') {
                            if(dp[i][j]==true || dp[i-1][j]==true) dp[i][j]=true;
                        }
                    }
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}