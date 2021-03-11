// Time Complexity : 0(mn)
// Space Complexity : 0(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

class RegularExpression {
    public boolean isMatch(String s, String p) {
        if(s==null || p==null){
            return false;
        }

        int sLen = s.length(), pLen = p.length();

        //initialize dp array
        boolean[][] dp = new boolean[sLen+1][pLen+1];
        //first element will always be true as blank will match blank
        dp[0][0] = true;

        //fill up the first row
        for (int i = 1; i <= pLen; i++) {
            if (p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }

        //iterate through the string
        //and for each character iterate through the pattern
        for(int i=1;i<=sLen;i++){
            for(int j=1;j<=pLen;j++){
                //normal characters
                if(p.charAt(j-1) != '*'){
                    //if s & p characters are equal or there is a dot
                    //then take value from top diagonal
                    if(p.charAt(j-1) == s.charAt(i-1) ||
                            p.charAt(j-1) == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }

                //if character is *
                else{
                    //zero case - take value from two steps behind
                    dp[i][j] = dp[i][j-2];

                    //if one case is available - if above is true
                    //then make current dp true
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        if(dp[i-1][j]){
                            dp[i][j] = true;
                        }
                    }
                }
            }
        }

        return dp[sLen][pLen];
    }
}
/*

    _   c   *   a   *   a
_   t   f   t   f   t   f
a   f   f   f   t   t   t
a   f   f   f   f   t   t
b   f   f   f   f   f   f

*/