//Time Complexity: O(MxN) where M is length og Source(s) string and N is length of Pattern(p) string, required to fill dp[m+1][n+1] matrix
//space complexity: O(MxN) where M is length og Source(s) string and N is length of Pattern(p) string, required to fill dp[m+1][n+1] matrix

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;
        int m = s.length();
        int n = p.length();
        boolean [][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        //filling first row in my dp matrix I have taken string as row and pattern as column
        for(int j=1;j<=n;j++){
            //here since for row 0 in DP matrix our string is empty so we have a case where * matches 0 character
            if (p.charAt(j-1) == '*')
                dp[0][j] = dp[0][j-2]; //this never goes out of bound because an * always has a previous element
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.' ){
                    dp[i][j] = dp[i-1][j-1];
                }else if (p.charAt(j-1) == '*'){
                    //0 case
                    dp[i][j] = dp[i][j-2];
                    //1 case, if preceding character in pattern matches current charcter in string
                    if (p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i-1][j] || dp[i][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
