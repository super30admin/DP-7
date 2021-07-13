public class RegularExpression {

    public boolean isMatch(String s, String p) {
         
        int mLength = s.length();
        int nLength = p.length();
        boolean[][] dp = new boolean[mLength+1][nLength+1];
        dp[0][0] = true;
        for(int j=1;j<dp[0].length;j++) {
            if(p.charAt(j-1) == '*') {
                dp[0][j] = dp[0][j-2];
            }   
        }
        
        for(int i=1;i<dp.length;i++) {
            for(int j=1;j<dp[0].length;j++) {
                if(p.charAt(j-1) != '*') {
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) =='.') {
                            dp[i][j] = dp[i-1][j-1];
                        }
                } else 
                    { 
                      //O case - get two steps behind
                    dp[i][j] = dp[i][j-2];
                    //Check if the preceeding matches with the current character or not
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) =='.') {
                        dp[i][j] = dp[i-1][j] || dp[i][j];
                    } 
                }
             }
        }
        return dp[mLength][nLength];
    }

    public static void main(String[] args) {
        RegularExpression regEx = new RegularExpression();
        boolean isMatch = regEx.isMatch("mississippi", "mis*is*p*.");
        System.out.println("Is match with the string: "+isMatch);

    }
}