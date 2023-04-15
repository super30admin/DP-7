/*
Regular Expression Matching
approach: we have choices at each * we encounter which is 2^n solution, to optimize use dp
time: O(mxn)
space: O(mxn)
 */
public class Problem1 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];

        dp[0][0] = true;
//        check top row
        for (int j = 1;j<dp[0].length;j++) {
            char c = p.charAt(j-1);
            if (c=='*') {
                dp[0][j] = dp[0][j-2];
            }
        }

        for (int i=1;i<dp.length;i++) {
            for (int j=1;j<dp[0].length;j++) {
                char c = p.charAt(j-1);
                char schar = s.charAt(i-1);

                if (c!='*') {
                    if (c==schar || c=='.') {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else {
//                    0 case
                    dp[i][j] = dp[i][j-2];

//                    1 case
                    if(p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-2)=='.') {
                        dp[i][j] = dp[i][j]||dp[i-1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
